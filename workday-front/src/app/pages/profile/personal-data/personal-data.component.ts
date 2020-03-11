import {Component, OnInit} from '@angular/core';
import {EmployeeService} from "../../../shared/services/employee/employee.service";
import {TokenStorageService} from "../../../core/services/security/token-storage.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Employee} from "../../../shared/models/employee.model";
import {Referential} from "../../../shared/models/referential.model";
import {parseDate, formatDate} from "../../../shared/utils/utils";

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
        console.log(data);
        this.employeeService.setStoredEmployee(data);
        this.employee = data as Employee;
        this.personalDataForm = this.createPersonalDataForm();
        this.employeeDataForm = this.createEmployeeDataForm();
        this.employeeDataForm = this.populateEmployeeDataForm();
      });
  }

  createPersonalDataForm(): FormGroup {
    this.personalDataForm = this.formBuilder.group({
      'lastName': [this.employee.lastName, [Validators.required, Validators.maxLength(30)]],
      'firstName': [this.employee.firstName, [Validators.required, Validators.maxLength(30)]],
      'gender': [this.employee.gender ? this.employee.gender.label: '', [Validators.required, Validators.maxLength(10)]],
      'birthPlace': [this.employee.birthPlace, [Validators.required, Validators.maxLength(13)]],
      'personIdentifier': [this.employee.personIdentifier, [Validators.required, Validators.minLength(13), Validators.maxLength(13)]],
      'birthDate': [this.employee.birthDate ? formatDate(this.employee.birthDate): '', [Validators.required]],
      'birthName': [this.employee.birthName, [Validators.required, Validators.maxLength(40)]],
      'email': [this.employee.email, [Validators.required, Validators.maxLength(40)]],
      'homePhoneNumber': [this.employee.homePhoneNumber, [Validators.maxLength(10)]],
      'mobilePhoneNumber': [this.employee.mobilePhoneNumber, [Validators.maxLength(10)]],
    });

    return this.personalDataForm;
  }

  createEmployeeDataForm(): FormGroup {
    this.employeeDataForm = this.formBuilder.group({
      'jobPosition': [this.employee.jobPosition ? this.employee.jobPosition.label : '', [Validators.required, Validators.maxLength(100)]],
      'entity': [this.employee.entity, [Validators.required, Validators.maxLength(30)]],
      'location': [this.employee.location, [Validators.required, Validators.maxLength(30)]],
      'department': [this.employee.department ? this.employee.department.label : '', [Validators.required, Validators.maxLength(20)]],
      'ITDeduction': [this.employee.ITDeduction, [Validators.required]],
      'joiningDate': [this.employee.joiningDate, [Validators.required]],
      'currentPositionStartingDate': [this.employee.currentPositionStartingDate, [Validators.required]],
    });
    return this.employeeDataForm;
  }

  populateEmployeeDataForm(): FormGroup {
    if (!this.employee.ITDeduction) {
      this.employee.ITDeduction = false;
      this.employeeDataForm.controls.ITDeduction.setValue(this.employee.ITDeduction);
      this.employeeDataForm.controls.ITDeduction.updateValueAndValidity();
    }
    return this.employeeDataForm;
  }

  putPersonalData() {
    this.employee.lastName = this.personalDataForm.controls.lastName.value;
    this.employee.firstName = this.personalDataForm.controls.firstName.value;
    if (!this.employee.gender) {
      this.employee.gender = new Referential();
    }
    this.employee.gender.label = this.personalDataForm.controls.gender.value;
    this.employee.birthPlace = this.personalDataForm.controls.birthPlace.value;
    this.employee.personIdentifier = this.personalDataForm.controls.personIdentifier.value;
    this.employee.birthDate =  parseDate(this.personalDataForm.controls.birthDate.value);
    this.employee.birthName = this.personalDataForm.controls.birthName.value;
    this.employee.email = this.personalDataForm.controls.email.value;
    this.employee.homePhoneNumber = this.personalDataForm.controls.homePhoneNumber.value;
    this.employee.mobilePhoneNumber = this.personalDataForm.controls.mobilePhoneNumber.value;

    console.log(this.employee);

    this.employeeService.putEmployee(this.employee).subscribe();
  }

  putEmployeeData() {


    this.employeeService.putEmployee(this.employee).subscribe();
  }

}
