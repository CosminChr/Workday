import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../shared/models/employee.model";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {HolidaysService} from "./holiday.service";
import {Holiday} from "../../shared/models/holiday.model";
import {dateDifference, formatDate, parseDate} from "../../shared/utils/utils";
import {range, reduce} from "lodash";
import {HolidayTypeEnum} from "../../shared/enums/holiday-type.enum";
import {HolidaysReferentialService} from "./holiday-referential.service";
import {Referential} from "../../shared/models/referential.model";
import {forkJoin} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HolidaysMessagingService} from "../../shared/services/websocket/holidays-messaging.service";
import {Notification} from "../../shared/models/notification.model";
import {NotificationService} from "../../shared/services/notification/notification.service";
import {NavbarService} from "../../shared/components/navbar/navbar.service";

declare var $: any;

@Component({
  selector: 'workday-holidays',
  templateUrl: './holidays.component.html',
  styleUrls: ['./holidays.component.scss']
})
export class HolidaysComponent implements OnInit, AfterViewInit {

  employee: Employee;

  yearsInCompany = new Array<number>();

  currentYearHolidaysByMonth = new Map<number, number>();

  sickDaysHolidaysByMonth = new Map<number, number>();

  selectedYear = 2020;

  holidays: Array<Holiday>;

  holidayReferentials: Array<Referential>;

  holidayForm: FormGroup;

  plannedHoliday = new Holiday();

  notifications = new Array<Notification>();

  constructor(private employeeService: EmployeeService,
              private holidayService: HolidaysService,
              private holidayReferentialService: HolidaysReferentialService,
              private holidaysMessagingService: HolidaysMessagingService,
              private notificationService: NotificationService,
              private navbarService: NavbarService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.employee = this.employeeService.getSavedEmployee();
    this.yearsInCompany = this.populateYearsInCompany();
    this.selectedYear = this.yearsInCompany[this.yearsInCompany.length - 1];

    forkJoin([
      this.holidayService.getHolidays(this.employee.id),
      this.holidayReferentialService.getHolidayReferentials(),
      this.notificationService.getNotificationsByEmployeeId(this.employee.id)
    ])
      .subscribe({
        next: (data) => {
          this.holidays = data[0] as Array<Holiday>;
          this.populateHolidayMapsByMonth();
          this.holidayReferentials = data[1] as Array<Referential>;
          this.notifications = data[2] as Array<Notification>;
          this.createHolidayForm();

          if (!this.holidaysMessagingService.stompClient.connected) {
            this.holidaysMessagingService.stompClient.connect({}, () => {
              this.holidaysMessagingService.stompClient.subscribe('/topic/employee/holidayRequest', (data) => {
                const holiday: Holiday = JSON.parse(data.body).body;
                this.holidays = this.holidays.filter(h => h.id !== holiday.id);
                this.holidays.push(holiday);
                const notification = new Notification();
                if (this.notifications && this.navbarService.getStoredEmployeeNotifications().value.length === 0) {
                  const lastId = Math.max.apply(null, this.notifications.map(item => item.id)) ;
                  notification.id =  lastId + 1;
                } else if (this.notifications) {
                  const lastId = Math.max.apply(null, this.navbarService.getStoredEmployeeNotifications().value.map(item => item.id));
                  notification.id = lastId + 1;
                }

                notification.message = 'Cererea ta de concediu pentru ' +
                  (new Date(holiday.fromDate).getTime() === new Date(holiday.toDate).getTime() ? formatDate(holiday.toDate) : 'perioada '
                    + holiday.fromDate + ' - ' + holiday.toDate) + ' a fost ' + (holiday.approved ? 'aprobată' : 'respinsă') + '.';
                notification.employee = this.employee;
                notification.active = true;
                this.notificationService.putNotification(notification)
                  .subscribe( data => {
                    this.navbarService.getStoredEmployeeNotifications().value.push(notification);
                    this.navbarService.setEmployeeNotifications(this.navbarService.getStoredEmployeeNotifications().value);
                  });
              });
            });
          } else {
            this.holidaysMessagingService.stompClient.subscribe('/topic/employee/holidayRequest', (data) => {
              const holiday: Holiday = JSON.parse(data.body).body;
              this.holidays = this.holidays.filter(h => h.id !== holiday.id);
              this.holidays.push(holiday);
              const notification = new Notification();
              if (this.notifications && this.navbarService.getStoredEmployeeNotifications().value.length === 0) {
                const lastId = Math.max.apply(null, this.notifications.map(item => item.id)) ;
                notification.id = lastId + 1;
              } else if (this.notifications) {
                const lastId = Math.max.apply(null, this.navbarService.getStoredEmployeeNotifications().value.map(item => item.id));
                notification.id = lastId + 1;
              }

              notification.message = 'Cererea ta de concediu pentru ' +
                (new Date(holiday.fromDate).getTime() === new Date(holiday.toDate).getTime() ? formatDate(holiday.toDate) : 'perioada '
                  + holiday.fromDate + ' - ' + holiday.toDate) + ' a fost ' + (holiday.approved ? 'aprobată' : 'respinsă') + '.';
              notification.employee = this.employee;
              notification.active = true;
              this.notificationService.putNotification(notification)
                .subscribe( data => {
                  this.navbarService.getStoredEmployeeNotifications().value.push(notification);
                  this.navbarService.setEmployeeNotifications(this.navbarService.getStoredEmployeeNotifications().value);
                });
            });
          }
        }, complete: () => {
          this.populateHolidayMapsByMonth();
        }
      });
  }


  ngAfterViewInit(): void {
    $('.selectpicker').selectpicker();
  }

  reinitializePicker() {
    $('.selectpicker').selectpicker('refresh');
  }


  populateYearsInCompany() {
    if (this.employee.joiningDate) {
      let i = Number(this.employee.joiningDate.toString().substring(0, 4));
      for (i; i <= new Date().getFullYear(); i++) {
        this.yearsInCompany.push(i);
      }
    }
    return this.yearsInCompany;
  }

  dateDifference(date1: Date, date2: Date) {
    let difference = dateDifference(date1, date2);
    return ++difference;
  }

  selectChangeHandler(event: any) {
    this.selectedYear = event.target.value;
  }

  yearsAreEqual(date1: Date, year: number): boolean {
    return new Date(date1).getFullYear() == year;
  }

  populateHolidayMapsByMonth() {

    for (let index of range(11)) {
      this.currentYearHolidaysByMonth.set(index, 0);
      this.currentYearHolidaysByMonth.set(index + 1, 0);
      this.sickDaysHolidaysByMonth.set(index, 0);
      this.sickDaysHolidaysByMonth.set(index + 1, 0);
    }

    if (this.holidays) {
      this.holidays.forEach(holiday => {
        for (let index of range(11)) {

          let numberOfDaysFirstMonth = 0;
          let numberOfDaysSecondMonth = 0;

          if (new Date(holiday.fromDate).getFullYear() === new Date(holiday.toDate).getFullYear()) {
            if (new Date(holiday.fromDate).getMonth() === new Date(holiday.toDate).getMonth() && new Date(holiday.fromDate).getMonth() == index) {
              numberOfDaysFirstMonth = numberOfDaysFirstMonth +
                this.dateDifference(holiday.fromDate, holiday.toDate);
              if (holiday.holidayType.label === HolidayTypeEnum.CURRENT_LEAVE && holiday.approved && holiday.validated) {
                this.currentYearHolidaysByMonth.set(index, this.currentYearHolidaysByMonth.get(index) + numberOfDaysFirstMonth);
              } else if (new Date(holiday.fromDate).getMonth() == index && holiday.holidayType.label === HolidayTypeEnum.SICK_DAYS && holiday.approved && holiday.validated) {
                this.sickDaysHolidaysByMonth.set(index, this.sickDaysHolidaysByMonth.get(index) + numberOfDaysFirstMonth);
              }
            } else if (new Date(holiday.fromDate).getMonth() == index && new Date(holiday.toDate).getMonth() == (index + 1)) {
              numberOfDaysFirstMonth = numberOfDaysFirstMonth +
                this.dateDifference(holiday.fromDate, new Date(new Date(holiday.fromDate).getFullYear(), new Date(holiday.fromDate).getMonth(), new Date(new Date(holiday.fromDate).getFullYear(), new Date(holiday.fromDate).getMonth() + 1, 0).getDate()));
              numberOfDaysSecondMonth = numberOfDaysSecondMonth +
                this.dateDifference(new Date(new Date(holiday.toDate).getFullYear(), new Date(holiday.toDate).getMonth(), 1), holiday.toDate);
              if (holiday.holidayType.label === HolidayTypeEnum.CURRENT_LEAVE && holiday.approved && holiday.validated) {
                this.currentYearHolidaysByMonth.set(index, this.currentYearHolidaysByMonth.get(index) + numberOfDaysFirstMonth);
                this.currentYearHolidaysByMonth.set(index + 1, this.currentYearHolidaysByMonth.get(index + 1) + numberOfDaysSecondMonth);
              } else if (new Date(holiday.fromDate).getMonth() == index && holiday.holidayType.label === HolidayTypeEnum.SICK_DAYS && holiday.approved && holiday.validated) {
                this.sickDaysHolidaysByMonth.set(index, this.sickDaysHolidaysByMonth.get(index) + numberOfDaysFirstMonth);
                this.sickDaysHolidaysByMonth.set(index + 1, this.sickDaysHolidaysByMonth.get(index + 1) + numberOfDaysSecondMonth);
              }
            }
          }
        }
      });
    }
  }

  getDaysUsedFromCurrentLeave(): number {
    return reduce(Array.from(this.currentYearHolidaysByMonth.values()), (sum, numberOfDays) => {
      return sum + numberOfDays;
    }, 0);
  }

  getDaysUsedFromSickDaysLeave(): number {
    return reduce(Array.from(this.sickDaysHolidaysByMonth.values()), (sum, numberOfDays) => {
      return sum + numberOfDays;
    }, 0);
  }

  createHolidayForm(): FormGroup {
    this.holidayForm = this.formBuilder.group({
      'holidayType': [this.plannedHoliday.holidayType, [Validators.required, Validators.maxLength(100)]],
      'fromDate': [this.plannedHoliday.fromDate ? formatDate(this.plannedHoliday.fromDate) : '', [Validators.required]],
      'toDate': [this.plannedHoliday.toDate ? formatDate(this.plannedHoliday.toDate) : '', [Validators.required]]
    });
    return this.holidayForm;
  }

  putHolidayRequest() {

    let holidayType = new Referential();
    holidayType.label = this.holidayForm.controls.holidayType.value;

    this.plannedHoliday.holidayType = holidayType;
    this.plannedHoliday.fromDate = parseDate(this.holidayForm.controls.fromDate.value);
    this.plannedHoliday.toDate = parseDate(this.holidayForm.controls.toDate.value);
    this.plannedHoliday.employee = this.employee;
    this.plannedHoliday.approved = false;
    this.plannedHoliday.validated = false;

    this.holidayService.putHoliday(this.plannedHoliday).subscribe(() => {
      this.holidayService.getHolidays(this.employee.id).subscribe({
        next: (data) => {
          this.holidays = data as Array<Holiday>;
          this.populateHolidayMapsByMonth();
        }, complete: () => {

          if (!this.holidaysMessagingService.stompClient.connected) {
            this.holidaysMessagingService.stompClient.connect({}, () => {
              this.holidaysMessagingService.sendHolidayRequest(this.employee.managerId);
            });
          } else {
            this.holidaysMessagingService.sendHolidayRequest(this.employee.managerId);
          }
        }
      });
    });
  }

  formatDate(date: Date): string {
    return formatDate(date);
  }
}
