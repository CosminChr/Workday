import { Component, OnInit } from '@angular/core';
import {Employee} from "../../shared/models/employee.model";
import {Overtime} from "../../shared/models/overtime.model";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {OvertimeService} from "./overtime.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {formatDate, parseDate} from "../../shared/utils/utils";
import {WorkFromHome} from "../../shared/models/work-from-home.model";
import {Holiday} from "../../shared/models/holiday.model";
import {OvertimeMessagingService} from "../../shared/services/websocket/overtime-messaging.service";
import {Notification} from "../../shared/models/notification.model";
import {NavbarService} from "../../shared/components/navbar/navbar.service";
import {NotificationService} from "../../shared/services/notification/notification.service";
import {WorkdayValidators} from "../../shared/validators/workday-validators";

@Component({
  selector: 'workday-overtime',
  templateUrl: './overtime.component.html',
  styleUrls: ['./overtime.component.scss']
})
export class OvertimeComponent implements OnInit {

  employee: Employee;

  newOvertime = new Overtime();

  overtimeList: Array<Overtime>;

  overtimeFormGroup: FormGroup;

  notifications = new Array<Notification>();

  constructor(private employeeService: EmployeeService,
              private overtimeService: OvertimeService,
              private overtimeMesaggingService: OvertimeMessagingService,
              private navbarService: NavbarService,
              private notificationService: NotificationService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.employee = this.employeeService.getSavedEmployee();

    this.overtimeService.getOvertimeList(this.employee.id).subscribe(data => {
      this.overtimeList = data as Array<Overtime>;
      this.createOvertimeFormGroup();
      this.overtimeMesaggingService.stompClient.connect({}, () => {
        this.overtimeMesaggingService.stompClient.subscribe('/topic/employee/overtime', (data) => {
          const overtime: Overtime = JSON.parse(data.body).body;
          this.overtimeList = this.overtimeList.filter(h => h.id !== overtime.id);
          this.overtimeList.push(overtime);
          const notification = new Notification();
          if (this.notifications && this.navbarService.getStoredEmployeeNotifications().value.length === 0) {
            const lastId = Math.max.apply(null, this.notifications.map(item => item.id)) ;
            notification.id =  lastId + 1;
          } else if (this.notifications) {
            const lastId = Math.max.apply(null, this.navbarService.getStoredEmployeeNotifications().value.map(item => item.id));
            notification.id = lastId + 1;
          }

          notification.message = 'Cererea ta pentru Overtime a fost ' + (overtime.approved ? 'aprobată' : 'respinsă') + '.';
          notification.employee = this.employee;
          notification.active = true;
          this.notificationService.putNotification(notification)
            .subscribe(data => {
              this.navbarService.getStoredEmployeeNotifications().value.push(notification);
              this.navbarService.setEmployeeNotifications(this.navbarService.getStoredEmployeeNotifications().value);
            });
        });
      });
    })
  }

  createOvertimeFormGroup(): FormGroup {

    let initiationDateAsString = new Date().getDate() + '-' +
      ((new Date().getMonth() + 1) < 10 ? '0' + (new Date().getMonth() + 1) :
        (new Date().getMonth() + 1)) + '-' + new Date().getFullYear();

    this.overtimeFormGroup = this.formBuilder.group({
      'numberOfHours': [this.newOvertime.numberOfHours, [Validators.required, WorkdayValidators.validNumber]],
      'effectuationDate': ['', [Validators.required, WorkdayValidators.validDate]],
      'initiationDate': [initiationDateAsString]
    });
    return this.overtimeFormGroup;
  }

  putOvertime() {
    this.newOvertime.numberOfHours = this.overtimeFormGroup.controls.numberOfHours.value;

    this.newOvertime.effectuationDate = parseDate(this.overtimeFormGroup.controls.effectuationDate.value);
    this.newOvertime.initiationDate = parseDate(this.overtimeFormGroup.controls.initiationDate.value);
    this.newOvertime.employee = this.employee;

    this.overtimeService.putOvertime(this.newOvertime).subscribe((data: Overtime) => {
      this.overtimeService.getOvertimeList(this.employee.id)
        .subscribe(  {
          next: (data) => {
            this.overtimeList = data as Array<Overtime>;
          }, complete: () => {
            this.overtimeMesaggingService.sendOvertimeRequest(this.employee.managerId);
          }
        })
    });
  }

  formatDate(date: Date): string {
    return formatDate(date);
  }
}


