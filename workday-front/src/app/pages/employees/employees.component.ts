import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {forkJoin} from "rxjs";
import {Employee} from "../../shared/models/employee.model";

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss']
})
export class EmployeesComponent implements OnInit {

  employees: Array<Employee>;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {

    forkJoin([
      this.employeeService.getEmployees()
    ])
      .subscribe( data => {
        this.employees = data[0];
      })
  }

  doEmployeesExist() {
    return this.employees && this.employees.length !== 0;
  }

  updateEmployee(employee: Employee) {

  }

  deleteEmployee(employee: Employee) {

  }

}
