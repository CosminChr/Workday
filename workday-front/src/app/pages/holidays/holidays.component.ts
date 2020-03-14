import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../shared/models/employee.model";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {HolidaysService} from "./holiday.service";
import {Holiday} from "../../shared/models/holiday.model";
import {dateDifference, formatDate, parseDate} from "../../shared/utils/utils";
import {range, reduce, find} from "lodash";
import {HolidayTypeEnum} from "../../shared/enums/holiday-type.enum";
import {HolidaysReferentialService} from "./holiday-referential.service";
import {Referential} from "../../shared/models/referential.model";
import {forkJoin} from "rxjs";
import {Form, FormBuilder, FormGroup, Validators} from "@angular/forms";

declare var $: any;

@Component({
  selector: 'app-holidays',
  templateUrl: './holidays.component.html',
  styleUrls: ['./holidays.component.scss']
})
export class HolidaysComponent implements OnInit, AfterViewInit {

  employee: Employee;

  yearsInCompany = new Array<number>();

  currentYearLeaveByMonth = new Map<number, number>();

  sickDaysLeaveByMonth = new Map<number, number>();

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
      .subscribe( data => {
        this.holidays = data[0] as Array<Holiday>;
        console.log(this.holidays);
        this.populateLeaveMapsByMonth();
        this.holidayReferentials = data[1] as Array<Referential>;
        this.createHolidayForm()
      });
  }


  ngAfterViewInit(): void {
    $('.selectpicker').selectpicker();
  }

  reinitializePicker() {
    $('.selectpicker').selectpicker('refresh');
  }


  populateYearsInCompany() {
    let i = Number(this.employee.joiningDate.toString().substring(0, 4));
    for (i; i <= new Date().getFullYear(); i++) {
      this.yearsInCompany.push(i);
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

  populateLeaveMapsByMonth() {

    for (let index of range(11)) {
      this.currentYearLeaveByMonth.set(index, 0);
      this.currentYearLeaveByMonth.set(index+1, 0);
      this.sickDaysLeaveByMonth.set(index, 0);
      this.sickDaysLeaveByMonth.set(index+1, 0);
    }

    this.holidays.forEach(holiday => {

      for (let index of range(11)) {

        let numberOfDaysFirstMonth = 0;
        let numberOfDaysSecondMonth = 0;

        if (new Date(holiday.fromDate).getFullYear() === new Date(holiday.toDate).getFullYear()) {
          if (new Date(holiday.fromDate).getMonth() === new Date(holiday.toDate).getMonth() === index) {
            numberOfDaysFirstMonth = numberOfDaysFirstMonth +
              dateDifference(holiday.fromDate, holiday.toDate);
          } else if (new Date(holiday.fromDate).getMonth() === index && new Date(holiday.toDate).getMonth() === (index + 1)) {
            numberOfDaysFirstMonth = numberOfDaysFirstMonth +
              dateDifference(holiday.fromDate, new Date(new Date(holiday.fromDate).getFullYear(), new Date(holiday.fromDate).getMonth(), new Date(new Date(holiday.fromDate).getFullYear(), new Date(holiday.fromDate).getMonth() + 1, 0).getDate()));
            numberOfDaysSecondMonth = numberOfDaysSecondMonth +
              dateDifference(new Date(new Date(holiday.toDate).getFullYear(), new Date(holiday.toDate).getMonth(), 1), holiday.toDate);
          }

          if (holiday.holidayType.label === HolidayTypeEnum.CURRENT_LEAVE) {
            this.currentYearLeaveByMonth.set(index, this.currentYearLeaveByMonth.get(index) + numberOfDaysFirstMonth);
            this.currentYearLeaveByMonth.set(index + 1, this.currentYearLeaveByMonth.get(index + 1) + numberOfDaysSecondMonth);

          } else {
            this.sickDaysLeaveByMonth.set(index, this.sickDaysLeaveByMonth.get(index) + numberOfDaysFirstMonth);
            this.sickDaysLeaveByMonth.set(index + 1, this.sickDaysLeaveByMonth.get(index + 1) + numberOfDaysSecondMonth);
          }
        }
      }
    });
  }

  getDaysUsedFromCurrentLeave(): number {
      return reduce(Array.from(this.currentYearLeaveByMonth.values()), (sum, numberOfDays) => {
        return sum + numberOfDays;
      }, 0);
  }

  getDaysUsedFromSickDaysLeave(): number {
    return reduce(Array.from(this.sickDaysLeaveByMonth.values()), (sum, numberOfDays) => {
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


    let referential: Referential = find(this.holidayReferentials, (ref) => {
        return ref.label = holidayType.label;
    });

    holidayType.id = referential.id;

    this.plannedHoliday.holidayType = holidayType;
    this.plannedHoliday.fromDate = parseDate(this.holidayForm.controls.from.value);
    this.plannedHoliday.toDate = parseDate(this.holidayForm.controls.to.value);
    this.plannedHoliday.employee = this.employee;
    this.plannedHoliday.approved = false;
    this.plannedHoliday.validated = false;
    this.holidayService.putHoliday(this.plannedHoliday).subscribe( () => {
      this.holidayService.getHolidays(this.employee.id).subscribe( data => {
        this.holidays = data as Array<Holiday>;
        this.populateLeaveMapsByMonth();
      });
    });
  }
}
