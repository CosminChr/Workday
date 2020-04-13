import { Component, OnInit } from '@angular/core';
import {Employee} from "../../shared/models/employee.model";
import {Overtime} from "../../shared/models/overtime.model";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {OvertimeService} from "./overtime.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {formatDate, parseDate} from "../../shared/utils/utils";
import {WorkFromHome} from "../../shared/models/work-from-home.model";

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

  constructor(private employeeService: EmployeeService,
              private overtimeService: OvertimeService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.employee = this.employeeService.getSavedEmployee();

    this.overtimeService.getOvertimeList(this.employee.id).subscribe(data => {
      this.overtimeList = data as Array<Overtime>;
      this.createOvertimeFormGroup();
    })
  }

  createOvertimeFormGroup(): FormGroup {

    let initiationDateAsString = new Date().getDate() + '-' +
      ((new Date().getMonth() + 1) < 10 ? '0' + (new Date().getMonth() + 1) :
        (new Date().getMonth() + 1)) + '-' + new Date().getFullYear();

    this.overtimeFormGroup = this.formBuilder.group({
      'numberOfHours': [this.newOvertime.numberOfHours, [Validators.required]],
      'effectuationDate': ['', [Validators.required]],
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
      this.overtimeList.push(data);
    });
  }
}


