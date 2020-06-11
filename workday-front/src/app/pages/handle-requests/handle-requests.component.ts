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

declare var $: any;

@Component({
  selector: 'app-handle-requests',
  templateUrl: './handle-requests.component.html',
  styleUrls: ['./handle-requests.component.scss']
})
export class HandleRequestsComponent implements OnInit {

  employee: Employee;

  employeesOfTheManager: Array<Employee>;

  holidays: Array<Holiday>;

  notifications = new Array<Notification>();

  constructor(private employeeService: EmployeeService,
              private holidayService: HolidaysService,
              private holidaysMessagingService: HolidaysMessagingService,
              private navbarService: NavbarService,
              private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.employee = this.employeeService.getSavedEmployee();

      forkJoin([
        this.employeeService.getEmployeesByManagerId(this.employee.managerId),
        this.notificationService.getNotificationsByEmployeeId(this.employee.id)
      ])
        .subscribe(data => {
          this.employeesOfTheManager = data[0];
          this.notifications = data[1];
          this.holidayService.getHolidaysForEmployees(this.employeesOfTheManager)
            .subscribe(
              data => {
                this.holidays = data;

                if (!this.holidaysMessagingService.stompClient.connected) {
                  this.holidaysMessagingService.stompClient.connect({}, () => {
                    this.holidaysMessagingService.stompClient.subscribe('/topic/manager', (data) => {
                      this.holidays = JSON.parse(data.body).body;
                      const notification = new Notification();
                      if (this.notifications && this.navbarService.getStoredManagerNotifications().value.length === 0) {
                         const lastId = Math.max.apply(null, this.notifications.map(item => item.id)) ;
                         notification.id =  lastId + 1;
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
                  });
                } else {
                  this.holidaysMessagingService.stompClient.subscribe('/topic/manager', (data) => {
                    this.holidays = JSON.parse(data.body).body;
                    this.navbarService.getStoredManagerNotifications().value;
                    const notification = new Notification();
                    if (this.navbarService.getStoredManagerNotifications().value.length === 0) {
                      const lastId = Math.max.apply(null, this.notifications.map(item => item.id)) ;
                      notification.id =  lastId + 1;
                    } else {
                      const lastId = Math.max.apply(null, this.navbarService.getStoredManagerNotifications().value.map(item => item.id));
                      notification.id = lastId + 1;
                    }

                    const receivedHolidayRequest: Holiday = this.holidays.reduce((prev, current) => (+prev.id > +current.id) ? prev : current);
                    notification.message = 'Ai primit o nouă cerere de aprobare concediu de la '
                      + receivedHolidayRequest.employee.firstName + ' ' + receivedHolidayRequest.employee.lastName  + '.';
                    notification.employee = this.employee;
                    notification.active = true;
                    this.notificationService.putNotification(notification)
                      .subscribe();
                    this.navbarService.getStoredManagerNotifications().value.push(notification);
                    this.navbarService.setManagerNotifications(this.navbarService.getStoredManagerNotifications().value);
                  });
                }
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

  doHolidaysThatNeedApprovalExist(): boolean {
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
}
