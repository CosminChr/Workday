import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../shared/models/employee.model";
import {EmployeeService} from "../../shared/services/employee/employee.service";

declare var require: any;
declare var $:any;

@Component({
  selector: 'app-holidays',
  templateUrl: './holidays.component.html',
  styleUrls: ['./holidays.component.scss']
})
export class HolidaysComponent implements OnInit, AfterViewInit {

  employee: Employee;

  yearsInCompany = new Array<number>();

  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    this.employee = this.employeeService.getSavedEmployee();
    this.yearsInCompany = this.populateYearsInCompany();
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
}
