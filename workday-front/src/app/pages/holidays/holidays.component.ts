import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../shared/models/employee.model";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {HolidaysService} from "./holiday.service";
import {Holiday} from "../../shared/models/holiday.model";
import {dateDifference} from "../../shared/utils/utils";

declare var $:any;

@Component({
  selector: 'app-holidays',
  templateUrl: './holidays.component.html',
  styleUrls: ['./holidays.component.scss']
})
export class HolidaysComponent implements OnInit, AfterViewInit {

  employee: Employee;

  yearsInCompany = new Array<number>();

  selectedYear = 2020;

  holidays: Array<Holiday>;

  constructor(private employeeService: EmployeeService,
              private holidayService: HolidaysService) { }

  ngOnInit() {
    this.employee = this.employeeService.getSavedEmployee();
    this.yearsInCompany = this.populateYearsInCompany();
    this.selectedYear = this.yearsInCompany[this.yearsInCompany.length-1];
    this.holidayService.getHolidays(this.employee.id).subscribe( data => {
      this.holidays = data as Array<Holiday>;
    });
  }


  ngAfterViewInit(): void {
    $('.selectpicker').selectpicker();
  }

  populateYearsInCompany() {
    let i = Number(this.employee.joiningDate.toString().substring(0,4));
    for (i; i <= new Date().getFullYear(); i++) {
      this.yearsInCompany.push(i);
    }
    return this.yearsInCompany;
  }

  dateDifference(date1: Date, date2: Date) {
    return dateDifference(date1, date2);
  }

  selectChangeHandler (event: any) {
    this.selectedYear = event.target.value;
  }

  yearsAreEqual(date1: Date, year: number): boolean {
    return new Date(date1).getFullYear() == year;
  }

}
