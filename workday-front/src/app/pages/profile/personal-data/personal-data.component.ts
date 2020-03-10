import {Component, OnInit} from '@angular/core';
import {EmployeeService} from "../../../shared/services/employee/employee.service";
import {TokenStorageService} from "../../../core/services/security/token-storage.service";
import {Form, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Employee} from "../../../shared/models/employee.model";

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.scss']
})
export class PersonalDataComponent implements OnInit {

  personalDataForm: FormGroup;

  employeeDataForm: FormGroup;

  employee: Employee;

  constructor(private employeeService: EmployeeService,
              private tokenStorageService: TokenStorageService,
              private formBuilder: FormBuilder) {
  }


  ngOnInit() {
    this.employeeService.getEmployee(this.tokenStorageService.getUser().username).subscribe(
      data => {
        this.employeeService.setStoredEmployee(data);
        this.employee = data as Employee;
        this.personalDataForm = this.createPersonalDataForm();
        this.employeeDataForm = this.createEmployeeDataForm();
      });


  }

  createPersonalDataForm(): FormGroup {
    this.personalDataForm = this.formBuilder.group({
      'lastName': [this.employee.lastName, [Validators.required, Validators.maxLength(30)]],
      'firstName': [this.employee.firstName, [Validators.required, Validators.maxLength(30)]],
      'gender': [this.employee.gender, [Validators.required, Validators.maxLength(10)]],
      'birthPlace': [this.employee.birthPlace, [Validators.required, Validators.maxLength(13)]],
      'personIdentifier': [this.employee.personIdentifier, [Validators.required, Validators.minLength(13),Validators.maxLength(13)]],
      'birthDate': [this.employee.birthDate, [Validators.required]],
      'birthName': [this.employee.birthName, [Validators.required, Validators.maxLength(40)]],
      'email': [this.employee.email, [Validators.required, Validators.maxLength(40)]],
      'homePhoneNumber': [this.employee.homePhoneNumber, [Validators.maxLength(10)]],
      'mobilePhoneNumber': [this.employee.mobilePhoneNumber, [Validators.maxLength(10)]],
    });

    return this.personalDataForm;
  }

  createEmployeeDataForm() {
    this.employeeDataForm = this.formBuilder.group({
      'lastName': [this.employee.lastName, [Validators.required, Validators.maxLength(30)]],
      'firstName': [this.employee.firstName, [Validators.required, Validators.maxLength(30)]],
      'gender': [this.employee.gender, [Validators.required, Validators.maxLength(10)]],
      'birthPlace': [this.employee.birthPlace, [Validators.required, Validators.maxLength(13)]],
      'personIdentifier': [this.employee.personIdentifier, [Validators.required, Validators.minLength(13),Validators.maxLength(13)]],
      'birthDate': [this.employee.birthDate, [Validators.required]],
      'birthName': [this.employee.birthName, [Validators.required, Validators.maxLength(40)]],
      'email': [this.employee.email, [Validators.required, Validators.maxLength(40)]],
      'homePhoneNumber': [this.employee.homePhoneNumber, [Validators.maxLength(10)]],
      'mobilePhoneNumber': [this.employee.mobilePhoneNumber, [Validators.maxLength(10)]],
    });
    return this.employeeDataForm;
  }
}
