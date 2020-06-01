import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../shared/models/employee.model";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {HolidaysService} from "./holiday.service";
import {Holiday} from "../../shared/models/holiday.model";
import {dateDifference, formatDate, parseDate} from "../../shared/utils/utils";
import {find, range, reduce} from "lodash";
import {HolidayTypeEnum} from "../../shared/enums/holiday-type.enum";
import {HolidaysReferentialService} from "./holiday-referential.service";
import {Referential} from "../../shared/models/referential.model";
import {forkJoin} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

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

  constructor(private employeeService: EmployeeService,
              private holidayService: HolidaysService,
              private holidayReferentialService: HolidaysReferentialService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.employee = this.employeeService.getSavedEmployee();
    this.yearsInCompany = this.populateYearsInCompany();
    this.selectedYear = this.yearsInCompany[this.yearsInCompany.length - 1];

    forkJoin([
      this.holidayService.getHolidays(this.employee.id),
      this.holidayReferentialService.getHolidayReferentials()
    ])
      .subscribe(data => {
        this.holidays = data[0] as Array<Holiday>;
        this.populateHolidayMapsByMonth();
        this.holidayReferentials = data[1] as Array<Referential>;
        this.createHolidayForm();
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

    this.holidays.forEach(holiday => {

      for (let index of range(10)) {

        let numberOfDaysFirstMonth = 0;
        let numberOfDaysSecondMonth = 0;

        if (new Date(holiday.fromDate).getFullYear() === new Date(holiday.toDate).getFullYear()) {
          if (new Date(holiday.fromDate).getMonth() === new Date(holiday.toDate).getMonth() == index) {
            numberOfDaysFirstMonth = numberOfDaysFirstMonth +
              this.dateDifference(holiday.fromDate, holiday.toDate);
          } else if (new Date(holiday.fromDate).getMonth() == index && new Date(holiday.toDate).getMonth() == (index + 1)) {
            numberOfDaysFirstMonth = numberOfDaysFirstMonth +
              this.dateDifference(holiday.fromDate, new Date(new Date(holiday.fromDate).getFullYear(), new Date(holiday.fromDate).getMonth(), new Date(new Date(holiday.fromDate).getFullYear(), new Date(holiday.fromDate).getMonth() + 1, 0).getDate()));
            numberOfDaysSecondMonth = numberOfDaysSecondMonth +
              this.dateDifference(new Date(new Date(holiday.toDate).getFullYear(), new Date(holiday.toDate).getMonth(), 1), holiday.toDate);
          }

          if (holiday.holidayType.label === HolidayTypeEnum.CURRENT_LEAVE && holiday.approved && holiday.validated) {
            this.currentYearHolidaysByMonth.set(index + 1, this.currentYearHolidaysByMonth.get(index + 1) + numberOfDaysFirstMonth);
            this.currentYearHolidaysByMonth.set(index + 2, this.currentYearHolidaysByMonth.get(index + 2) + numberOfDaysSecondMonth);

          } else if (holiday.holidayType.label === HolidayTypeEnum.SICK_DAYS && holiday.approved && holiday.validated) {
            this.sickDaysHolidaysByMonth.set(index + 1, this.sickDaysHolidaysByMonth.get(index + 1) + numberOfDaysFirstMonth);
            this.sickDaysHolidaysByMonth.set(index + 2, this.sickDaysHolidaysByMonth.get(index + 2) + numberOfDaysSecondMonth);
          }
        }
      }
    });
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

  putHoliday() {

    let holidayType = new Referential();
    holidayType.label = this.holidayForm.controls.holidayType.value;

    this.plannedHoliday.holidayType = holidayType;
    this.plannedHoliday.fromDate = parseDate(this.holidayForm.controls.fromDate.value);
    this.plannedHoliday.toDate = parseDate(this.holidayForm.controls.toDate.value);
    this.plannedHoliday.employee = this.employee;
    this.plannedHoliday.approved = false;
    this.plannedHoliday.validated = false;

    this.holidayService.putHoliday(this.plannedHoliday).subscribe(() => {
      this.holidayService.getHolidays(this.employee.id).subscribe(data => {
        this.holidays = data as Array<Holiday>;
        this.populateHolidayMapsByMonth();
      });
    });
  }

  formatDate(date: Date): string {
    return formatDate(date);
  }
}
