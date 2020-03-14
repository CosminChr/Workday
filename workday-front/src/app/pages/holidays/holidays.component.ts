import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../shared/models/employee.model";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {HolidaysService} from "./holiday.service";
import {Holiday} from "../../shared/models/holiday.model";
import {dateDifference} from "../../shared/utils/utils";
import {range, reduce} from "lodash";
import {HolidayTypeEnum} from "../../shared/enums/holiday-type.enum";

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

  constructor(private employeeService: EmployeeService,
              private holidayService: HolidaysService) {
  }

  ngOnInit() {
    this.employee = this.employeeService.getSavedEmployee();
    this.yearsInCompany = this.populateYearsInCompany();
    this.selectedYear = this.yearsInCompany[this.yearsInCompany.length - 1];
    this.holidayService.getHolidays(this.employee.id).subscribe(data => {
      this.holidays = data as Array<Holiday>;
      this.populateLeaveMapsByMonth();
    });

  }


  ngAfterViewInit(): void {
    $('.selectpicker').selectpicker();
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

        if (new Date(holiday.from).getFullYear() === new Date(holiday.to).getFullYear()) {
          if (new Date(holiday.from).getMonth() === new Date(holiday.to).getMonth() === index) {
            numberOfDaysFirstMonth = numberOfDaysFirstMonth +
              dateDifference(holiday.from, holiday.to);
          } else if (new Date(holiday.from).getMonth() === index && new Date(holiday.to).getMonth() === (index + 1)) {
            numberOfDaysFirstMonth = numberOfDaysFirstMonth +
              dateDifference(holiday.from, new Date(new Date(holiday.from).getFullYear(), new Date(holiday.from).getMonth(), new Date(new Date(holiday.from).getFullYear(), new Date(holiday.from).getMonth() + 1, 0).getDate()));
            numberOfDaysSecondMonth = numberOfDaysSecondMonth +
              dateDifference(new Date(new Date(holiday.to).getFullYear(), new Date(holiday.to).getMonth(), 1), holiday.to);
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
}
