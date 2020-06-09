import {Component, OnInit} from '@angular/core';
import {Employee} from "../../shared/models/employee.model";
import {Holiday} from "../../shared/models/holiday.model";
import {Referential} from "../../shared/models/referential.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {HolidaysService} from "../holidays/holiday.service";
import {dateDifference, formatDate, parseDate} from "../../shared/utils/utils";
import {forkJoin} from "rxjs";

declare var $: any;

@Component({
  selector: 'app-handle-requests',
  templateUrl: './handle-requests.component.html',
  styleUrls: ['./handle-requests.component.scss']
})
export class HandleRequestsComponent implements OnInit {

  employee: Employee;

  employeesOfTheManager: Array<Employee>;

  yearsInCompany = new Array<number>();

  currentYearHolidaysByMonth = new Map<number, number>();

  sickDaysHolidaysByMonth = new Map<number, number>();

  selectedYear = 2020;

  holidays: Array<Holiday>;

  holidayReferentials: Array<Referential>;

  holidayForm: FormGroup;

  constructor(private employeeService: EmployeeService,
              private holidayService: HolidaysService,
              private formBuilder: FormBuilder) {
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
              console.log(data);
              this.holidays = data;
            })
      });
  }

  formatDate(date: Date): string {
    return formatDate(date);
  }

  dateDifference(date1: Date, date2: Date) {
    let difference = dateDifference(date1, date2);
    return ++difference;
  }

  doHolidaysThatNeedsApprovalExist():boolean {
    if (this.holidays) {
      return this.holidays.map(holiday => holiday.approved).filter(approved => approved !== true).length !== 0;
    }
    return false;
  }

  validateHolidayRequest(holiday: Holiday) {
    console.log(holiday.id);
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
