import {Component, OnInit} from '@angular/core';
import {Employee} from "../../shared/models/employee.model";
import {Holiday} from "../../shared/models/holiday.model";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {HolidaysService} from "../holidays/holiday.service";
import {dateDifference, formatDate} from "../../shared/utils/utils";
import {forkJoin} from "rxjs";
import {NavbarService} from "../../shared/components/navbar/navbar.service";
import {Notification} from "../../shared/models/notification.model";
import {NotificationService} from "../../shared/services/notification/notification.service";
import {HolidaysMessagingService} from "../../shared/services/websocket/holidays-messaging.service";
import {WorkFromHomeService} from "../work-from-home/work-from-home.service";
import {WorkFromHome} from "../../shared/models/work-from-home.model";
import {Referential} from "../../shared/models/referential.model";
import {WorkFromHomeMessagingService} from "../../shared/services/websocket/work-from-home.service";

declare var $: any;

@Component({
  selector: 'app-handle-requests',
  templateUrl: './handle-requests.component.html',
  styleUrls: ['./handle-requests.component.scss']
})
export class HandleRequestsComponent implements OnInit {

  employee: Employee;

  employeesOfManager: Array<Employee>;

  holidays: Array<Holiday>;

  notifications = new Array<Notification>();

  workFromHomeListOfManager: Array<WorkFromHome>;

  constructor(private employeeService: EmployeeService,
              private holidayService: HolidaysService,
              private holidaysMessagingService: HolidaysMessagingService,
              private workFromHomeMessagingService: WorkFromHomeMessagingService,
              private navbarService: NavbarService,
              private notificationService: NotificationService,
              private workFromHomeService: WorkFromHomeService) {
  }

  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();

      forkJoin([
        this.employeeService.getEmployeesByManagerId(this.employee.managerId),
        this.notificationService.getNotificationsByEmployeeId(this.employee.id),
        this.workFromHomeService.getWorkFromHomeForEmployeesOfManager(this.employee.managerId)
      ])
        .subscribe(data => {
          this.employeesOfManager = data[0] as Array<Employee>;
          this.notifications = data[1] as Array<Notification>;
          this.workFromHomeListOfManager = data[2] as Array<WorkFromHome>;
          this.holidayService.getHolidaysForEmployees(this.employeesOfManager)
            .subscribe(
              data => {
                this.holidays = data;
                  this.holidaysMessagingService.stompClient.connect({}, () => {
                    this.holidaysMessagingService.stompClient.subscribe('/topic/manager/holiday', (data) => {
                      this.holidays = JSON.parse(data.body).body;
                      const notification = new Notification();
                      if (this.notifications && this.navbarService.getStoredManagerNotifications().value.length === 0) {
                        const lastId = Math.max.apply(null, this.notifications.map(item => item.id));
                        notification.id = lastId + 1;
                      } else if (this.notifications) {
                        const lastId = Math.max.apply(null, this.navbarService.getStoredManagerNotifications().value.map(item => item.id));
                        notification.id = lastId + 1;
                      }

                      const receivedHolidayRequest: Holiday = this.holidays.reduce((prev, current) => (+prev.id > +current.id) ? prev : current);
                      notification.message = 'Ai primit o nouă cerere de aprobare concediu de la '
                        + receivedHolidayRequest.employee.firstName + ' ' + receivedHolidayRequest.employee.lastName + '.';
                      notification.employee = this.employee;
                      notification.active = true;
                      this.notificationService.putNotification(notification)
                        .subscribe();
                      this.navbarService.getStoredManagerNotifications().value.push(notification);
                      this.navbarService.setManagerNotifications(this.navbarService.getStoredManagerNotifications().value);
                    });

                    this.workFromHomeMessagingService.stompClient.subscribe('/topic/manager/workFromHome', (data) => {
                      this.workFromHomeListOfManager = JSON.parse(data.body).body;
                      const notification = new Notification();
                      if (this.notifications && this.navbarService.getStoredManagerNotifications().value.length === 0) {
                        const lastId = Math.max.apply(null, this.notifications.map(item => item.id)) ;
                        notification.id =  lastId + 1;
                      } else if (this.notifications) {
                        const lastId = Math.max.apply(null, this.navbarService.getStoredManagerNotifications().value.map(item => item.id));
                        notification.id = lastId + 1;
                      }
                      const receivedWorkFromHomeRequest: WorkFromHome = this.workFromHomeListOfManager[0];
                      notification.message = 'Ai primit o nouă cerere de aprobare Work From Home de la '
                        + receivedWorkFromHomeRequest.employee.firstName + ' ' + receivedWorkFromHomeRequest.employee.lastName + '.';
                      notification.employee = this.employee;
                      notification.active = true;
                      this.notificationService.putNotification(notification)
                        .subscribe();
                      this.navbarService.getStoredManagerNotifications().value.push(notification);
                      this.navbarService.setManagerNotifications(this.navbarService.getStoredManagerNotifications().value);
                    });
                  });
              });
        });
  }

  formatDate(date: Date): string {
    return formatDate(date);
  }

  dateDifference(date1: Date, date2: Date) {
    let difference = dateDifference(date1, date2);
    return ++difference;
  }

  doHolidayRequestsThatNeedApprovalExist(): boolean {
    if (this.holidays) {
      return this.holidays.map(holiday => holiday.validated).filter(validated => validated !== true).length !== 0;
    }
    return false;
  }

  validateHolidayRequest(holiday: Holiday) {
    holiday.approved = true;
    holiday.rejected = false;
    holiday.validated = true;
    this.updateHoliday(holiday);
  }

  rejectHolidayRequest(holiday: Holiday) {
    holiday.approved = false;
    holiday.rejected = true;
    holiday.validated = true;
    this.updateHoliday(holiday);
  }

  updateHoliday(holiday: Holiday) {
    this.holidayService.putHoliday(holiday)
      .subscribe({
        complete : () => {
          this.holidaysMessagingService.handleHolidayRequest(holiday.id);
        }
      });
  }

  doWorkFromHomeRequestsThatNeedApprovalExist(): boolean {
    return this.workFromHomeListOfManager && this.workFromHomeListOfManager
      .filter(workFromHome => this.needsApproval(workFromHome.lastInitiationDate, workFromHome.lastProcessingDate)).length > 0;
  }

  validateWorkFromHomeRequest(workFromHome: WorkFromHome) {
    workFromHome.lastProcessingDate = new Date();
    if (workFromHome.potentialDayOfWeekDay1) {
      if (!workFromHome.dayOfWeekDay1) {
        workFromHome.dayOfWeekDay1 = new Referential();
      }
      workFromHome.dayOfWeekDay1.label = workFromHome.potentialDayOfWeekDay1.label;
      workFromHome.potentialDayOfWeekDay1 = null;
      if (!workFromHome.startDateDay1) {
        workFromHome.startDateDay1 = workFromHome.lastInitiationDate;
      }
    }
    if (workFromHome.potentialDayOfWeekDay2) {
      if (!workFromHome.dayOfWeekDay2) {
        workFromHome.dayOfWeekDay2 = new Referential();
      }
      workFromHome.dayOfWeekDay2.label = workFromHome.potentialDayOfWeekDay2.label;
      workFromHome.potentialDayOfWeekDay2 = null;
      if (!workFromHome.startDateDay2) {
        workFromHome.startDateDay2 = workFromHome.lastInitiationDate;
      }
    }
    workFromHome.approved = true;
    this.updateWorkFromHome(workFromHome);
  }

  rejectWorkFromHomeRequest(workFromHome: WorkFromHome) {
    workFromHome.lastProcessingDate = new Date();
    workFromHome.potentialDayOfWeekDay1 = null;
    workFromHome.potentialDayOfWeekDay2 = null;
    workFromHome.approved = false;
    this.updateWorkFromHome(workFromHome);
  }

  updateWorkFromHome(workFromHome: WorkFromHome) {
    this.workFromHomeService.putWorkFromHome(workFromHome)
      .subscribe({
        complete : () => {
           this.workFromHomeMessagingService.handleWorkFromHomeRequest(workFromHome.id);
        }
      });
  }

  needsApproval(initiationDate, processingDate): boolean {
    return new Date(initiationDate).getTime() > new Date(processingDate).getTime();
  }
}
