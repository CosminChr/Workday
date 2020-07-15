import {AfterViewInit, Component, OnInit} from '@angular/core';
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {DayOfWeekReferentialService} from "./day-of-week-referential.service";
import {WorkFromHomeService} from "./work-from-home.service";
import {forkJoin} from "rxjs";
import {Employee} from "../../shared/models/employee.model";
import {WorkFromHome} from "../../shared/models/work-from-home.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Referential} from "../../shared/models/referential.model";
import {dateDifference, formatDate, parseDate} from "../../shared/utils/utils";
import {WorkFromHomeMessagingService} from "../../shared/services/websocket/work-from-home-messaging.service";
import {Notification} from "../../shared/models/notification.model";
import {NavbarService} from "../../shared/components/navbar/navbar.service";
import {NotificationService} from "../../shared/services/notification/notification.service";

declare var $: any;

@Component({
  selector: 'workday-work-from-home',
  templateUrl: './work-from-home.component.html',
  styleUrls: ['./work-from-home.component.scss']
})
export class WorkFromHomeComponent implements OnInit, AfterViewInit {

  employee: Employee;

  dayOfWeekReferentials: Array<Referential>;

  workFromHome: WorkFromHome;

  workFromHomeFormGroup: FormGroup;

  currentDayOfWeekDay1: string;

  currentDayOfWeekDay2: string;

  notifications = new Array<Notification>();


  constructor(private employeeService: EmployeeService,
              private dayOfWeekReferentialService: DayOfWeekReferentialService,
              private workFromHomeService: WorkFromHomeService,
              private workFromHomeMessagingService: WorkFromHomeMessagingService,
              private navbarService: NavbarService,
              private notificationService: NotificationService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();

    forkJoin([
      this.workFromHomeService.getWorkFromHome(this.employee.id),
      this.dayOfWeekReferentialService.getDayOfWeekReferentials(),
      this.notificationService.getNotificationsByEmployeeId(this.employee.id)
    ]).subscribe(data => {
      this.workFromHome = data[0] as WorkFromHome;
      this.dayOfWeekReferentials = data[1] as Array<Referential>;
      this.notifications = data[2] as Array<Notification>;
      this.createWorkfromHomeForm();
      if (!this.workFromHomeMessagingService.stompClient.connected) {
        this.workFromHomeMessagingService.stompClient.connect({}, () => {
          this.workFromHomeMessagingService.stompClient.subscribe('/topic/employee/workFromHome', (data) => {
            const workFromHome: WorkFromHome = JSON.parse(data.body).body;
            this.workFromHome = workFromHome;
            this.workFromHomeFormGroup.controls.startDateDay1
              .setValue(this.workFromHome.startDateDay1 ? formatDate(this.workFromHome.startDateDay1) : '');
            this.workFromHomeFormGroup.controls.startDateDay1.updateValueAndValidity();

            this.workFromHomeFormGroup.controls.startDateDay2
              .setValue(this.workFromHome.startDateDay2 ? formatDate(this.workFromHome.startDateDay2) : '');
            this.workFromHomeFormGroup.controls.startDateDay2.updateValueAndValidity();

            setTimeout(function () {
              $('.selectpicker').selectpicker();

              $('#dayOfWeekDay1').selectpicker('val', workFromHome.dayOfWeekDay1?.label);
              $('#dayOfWeekDay1').selectpicker('refresh');

              $('#dayOfWeekDay2').selectpicker('val', workFromHome.dayOfWeekDay2?.label);
              $('#dayOfWeekDay2').selectpicker('refresh');

            }, 200);

            if (workFromHome.potentialDayOfWeekDay1 || workFromHome.potentialDayOfWeekDay2) {
              setTimeout(function () {
                $('#dayOfWeekDay1').selectpicker('val', workFromHome.dayOfWeekDay1?.label);
                $('#dayOfWeekDay1').prop("disabled", true);
                $('#dayOfWeekDay1').selectpicker('refresh');

              }, 200);
            }

            if (workFromHome.potentialDayOfWeekDay1 || workFromHome.potentialDayOfWeekDay2) {
              setTimeout(function () {
                $('#dayOfWeekDay2').selectpicker('val', workFromHome.dayOfWeekDay2?.label);
                $('#dayOfWeekDay2').prop("disabled", true);
                $('#dayOfWeekDay2').selectpicker('refresh');

              }, 200);
            }

            if (!workFromHome.potentialDayOfWeekDay1 && !workFromHome.potentialDayOfWeekDay2) {
              setTimeout(function () {

                $('#dayOfWeekDay1').selectpicker('val', workFromHome.dayOfWeekDay1?.label);
                $('#dayOfWeekDay1').prop("disabled", false);
                $('#dayOfWeekDay1').selectpicker('refresh');

                $('#dayOfWeekDay2').selectpicker('val', workFromHome.dayOfWeekDay2?.label);
                $('#dayOfWeekDay2').prop("disabled", false);
                $('#dayOfWeekDay2').selectpicker('refresh');

              }, 200);
            }

            this.currentDayOfWeekDay1 = null;
            this.currentDayOfWeekDay2 = null;

            const notification = new Notification();
            if (this.notifications && this.navbarService.getStoredEmployeeNotifications().value.length === 0) {
              const lastId = Math.max.apply(null, this.notifications.map(item => item.id));
              notification.id = lastId + 1;
            } else if (this.notifications) {
              const lastId = Math.max.apply(null, this.navbarService.getStoredEmployeeNotifications().value.map(item => item.id));
              notification.id = lastId + 1;
            }

            notification.message = 'Cererea ta pentru Work From Home a fost ' + (workFromHome.approved ? 'aprobată' : 'respinsă') + '.';
            notification.employee = this.employee;
            notification.active = true;
            this.notificationService.putNotification(notification)
              .subscribe(data => {
                this.navbarService.getStoredEmployeeNotifications().value.push(notification);
                this.navbarService.setEmployeeNotifications(this.navbarService.getStoredEmployeeNotifications().value);

              });
          });
        });
      }
    });
  }

  ngAfterViewInit(): void {

    this.workFromHomeService.getWorkFromHome(this.employee.id)
      .subscribe((data: WorkFromHome) => {
        setTimeout(function () {
          $('.selectpicker').selectpicker();

          $('#dayOfWeekDay1').selectpicker('val', data.dayOfWeekDay1?.label);
          $('#dayOfWeekDay1').selectpicker('refresh');

          $('#dayOfWeekDay2').selectpicker('val', data.dayOfWeekDay2?.label);
          $('#dayOfWeekDay2').selectpicker('refresh');

        }, 200);
      });

    this.workFromHomeService.getWorkFromHome(this.employee.id)
      .subscribe((data: WorkFromHome) => {

        if (data.potentialDayOfWeekDay1 || data.potentialDayOfWeekDay2) {
          setTimeout(function () {
            $('#dayOfWeekDay1').selectpicker('val', data.dayOfWeekDay1?.label);
            $('#dayOfWeekDay1').prop("disabled", true);
            $('#dayOfWeekDay1').selectpicker('refresh');

          }, 200);
        }

        if (data.potentialDayOfWeekDay1 || data.potentialDayOfWeekDay2) {
          setTimeout(function () {
            $('#dayOfWeekDay2').selectpicker('val', data.dayOfWeekDay2?.label);
            $('#dayOfWeekDay2').prop("disabled", true);
            $('#dayOfWeekDay2').selectpicker('refresh');

          }, 200);
        }

        setTimeout(function () {
          $('#joiningDate').prop("disabled", true);
          $('#joiningDate').selectpicker('refresh');

        }, 200);
      });
  }


  createWorkfromHomeForm(): FormGroup {
    this.workFromHomeFormGroup = this.formBuilder.group({
      'joiningDate': [this.employee.joiningDate ? formatDate(this.employee.joiningDate) : '', [Validators.required]],
      'startDateDay1': [this.workFromHome.startDateDay1 ? formatDate(this.workFromHome.startDateDay1) : '', [Validators.required]],
      'startDateDay2': [this.workFromHome.startDateDay2 ? formatDate(this.workFromHome.startDateDay2) : '', [Validators.required]],
      'dayOfWeekDay1': [this.currentDayOfWeekDay1 ? this.currentDayOfWeekDay1 : this.workFromHome.dayOfWeekDay1?.label, [Validators.required, Validators.maxLength(13)]],
      'dayOfWeekDay2': [this.currentDayOfWeekDay2 ? this.currentDayOfWeekDay2 : this.workFromHome.dayOfWeekDay2?.label, [Validators.required, Validators.minLength(13), Validators.maxLength(13)]]
    });

    if (!this.isStartDateDay1Eligibile() || this.workFromHome?.startDateDay1) {
      this.workFromHomeFormGroup.get('startDateDay1').disable();
      $('#dayOfWeekDay1').selectpicker('refresh');
    }

    if (!this.isStartDateDay1Eligibile()) {
      setTimeout(function () {
        $('#dayOfWeekDay1').prop("disabled", true);
      }, 200);
    }

    if (!this.isStartDateDay2Eligibile() || this.workFromHome?.startDateDay2) {
      this.workFromHomeFormGroup.get('startDateDay2').disable();
      $('#dayOfWeekDay2').selectpicker('refresh');
    }

    if (!this.isStartDateDay2Eligibile()) {
      setTimeout(function () {
        $('#dayOfWeekDay2').prop("disabled", true);
      }, 200);
    }
    return this.workFromHomeFormGroup;
  }

  isStartDateDay1Eligibile(): boolean {
    return Math.floor(dateDifference(this.employee.joiningDate, new Date()) / 30) >= 6 && !this.workFromHome.potentialDayOfWeekDay1;
  }

  isStartDateDay2Eligibile(): boolean {
    return Math.floor(dateDifference(this.employee.joiningDate, new Date()) / 30) >= 12 && !this.workFromHome.potentialDayOfWeekDay2;
  }

  onDayOfWeekDay1Change(event) {
    this.currentDayOfWeekDay1 = this.dayOfWeekReferentials[event.target.selectedIndex - 1].label;
  }

  onDayOfWeekDay2Change(event) {
    this.currentDayOfWeekDay2 = this.dayOfWeekReferentials[event.target.selectedIndex - 1].label;
  }

  putWorkFromHome() {
    if (this.currentDayOfWeekDay1) {
      this.workFromHome.potentialDayOfWeekDay1 = new Referential();
      this.workFromHome.potentialDayOfWeekDay1.label = this.currentDayOfWeekDay1;
    }
    if (this.currentDayOfWeekDay2) {
      this.workFromHome.potentialDayOfWeekDay2 = new Referential();
      this.workFromHome.potentialDayOfWeekDay2.label = this.currentDayOfWeekDay2;
    }

    this.workFromHome.startDateDay1 = this.workFromHomeFormGroup.controls.startDateDay1.value != '' ? parseDate(this.workFromHomeFormGroup.controls.startDateDay1.value) : null;

    this.workFromHome.startDateDay2 = this.workFromHomeFormGroup.controls.startDateDay2.value != '' ? parseDate(this.workFromHomeFormGroup.controls.startDateDay2.value) : null;

    this.workFromHome.employee = this.employee;
    this.workFromHome.lastInitiationDate = new Date();


    this.workFromHomeService.putWorkFromHome(this.workFromHome)
      .subscribe({
        next: (data: WorkFromHome) => {
          this.workFromHome = data;

          if (data.potentialDayOfWeekDay1 || data.potentialDayOfWeekDay2) {
            setTimeout(function () {
              $('#dayOfWeekDay1').selectpicker('val', data.dayOfWeekDay1?.label);
              $('#dayOfWeekDay1').prop("disabled", true);
              $('#dayOfWeekDay1').selectpicker('refresh');

            }, 200);
          }

          if (data.potentialDayOfWeekDay1 || data.potentialDayOfWeekDay2) {
            setTimeout(function () {
              $('#dayOfWeekDay2').selectpicker('val', data.dayOfWeekDay2?.label);
              $('#dayOfWeekDay2').prop("disabled", true);
              $('#dayOfWeekDay2').selectpicker('refresh');

            }, 200);
          }
        }, complete: () => {
          this.workFromHomeMessagingService.sendWorkFromHomeRequest(this.workFromHome.employee.managerId);
        }
      });
  }
}
