import {Component, OnInit} from '@angular/core';
import {Employee} from "../../shared/models/employee.model";
import {Holiday} from "../../shared/models/holiday.model";
import {Referential} from "../../shared/models/referential.model";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {HolidaysService} from "../holidays/holiday.service";
import {dateDifference, formatDate} from "../../shared/utils/utils";
import {forkJoin} from "rxjs";
import {HolidaysMessagingService} from "../holidays/holidays-messaging.service";
import {NavbarService} from "../../shared/components/navbar/navbar.service";
import {Notification} from "../../shared/models/notification.model";

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

  constructor(private employeeService: EmployeeService,
              private holidayService: HolidaysService,
              private holidaysMessagingService: HolidaysMessagingService,
              private navbarService: NavbarService) {
  }

  ngOnInit() {
    this.employee = this.employeeService.getSavedEmployee();

    forkJoin([
      this.employeeService.getEmployeesByManagerId(this.employee.managerId)
    ])
      .subscribe(data => {
        this.employeesOfTheManager = data[0];
        this.holidayService.getHolidaysForEmployees(this.employeesOfTheManager)
          .subscribe(
            data => {
              this.holidays = data;
              this.holidaysMessagingService.stompClient.subscribe('/topic/manager', (data) => {
                this.holidays = JSON.parse(data.body).body;
                const notification = new Notification();
                const receivedHolidayRequest: Holiday = this.holidays.reduce((prev, current) => (+prev.id > +current.id) ? prev : current);
                notification.message = 'Ai primit o nouă cerere de aprobare concediu de la '
                  + receivedHolidayRequest.employee.firstName + ' ' + receivedHolidayRequest.employee.lastName;

                this.navbarService.getStoredManagerNotifications().value.push(notification);
                this.navbarService.setManagerNotifications(this.navbarService.getStoredManagerNotifications().value);
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
      .subscribe();
  }

}
