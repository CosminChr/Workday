import {AfterViewInit, Component, OnInit} from '@angular/core';
import {EmployeeService} from "../../../shared/services/employee/employee.service";
import {TokenStorageService} from "../../../core/services/security/token-storage.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Employee} from "../../../shared/models/employee.model";
import {Referential} from "../../../shared/models/referential.model";
import {formatDate, parseDate} from "../../../shared/utils/utils";
import {DepartmentReferentialService} from "./department-referential.service";
import {forkJoin} from "rxjs";
import {Admin} from "../../../shared/models/admin.model";
import {Router} from "@angular/router";
import {GenderReferentialService} from "../marital-status/gender-referential.service";
import {JobPositionReferentialService} from "./job-position-referential.service";
import {WorkdayValidators} from "../../../shared/validators/workday-validators";
import {NotificationService} from "../../../shared/services/notification/notification.service";

declare var $: any;

@Component({
  selector: 'workday-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.scss']
})
export class PersonalDataComponent implements OnInit, AfterViewInit {

  personalDataForm: FormGroup;

  employeeDataForm: FormGroup;

  employee: Employee;

  departmentReferentials: Array<Referential>;

  genderReferentials: Array<Referential>;

  jobPositionReferentials: Array<Referential>;

  manager: Employee;

  constructor(private employeeService: EmployeeService,
              private tokenStorageService: TokenStorageService,
              private departmentReferentialService: DepartmentReferentialService,
              private genderReferentialService: GenderReferentialService,
              private jobPositionReferentialService: JobPositionReferentialService,
              private notificationService: NotificationService,
              private formBuilder: FormBuilder,
              private router: Router) {
  }


  ngOnInit() {

    if (this.tokenStorageService.getUser().roles.filter(role => role === 'Admin').length === 0) {
      forkJoin([
        this.employeeService.getEmployee(this.tokenStorageService.getUser().username),
        this.departmentReferentialService.getDepartmentReferentials(),
        this.genderReferentialService.getGenderReferentials(),
        this.jobPositionReferentialService.getJobPositionReferentials()
      ])
        .subscribe(data => {
          this.employee = data[0] as Employee;
          this.employeeService.setStoredEmployee(this.employee);
          this.departmentReferentials = data[1] as Array<Referential>;
          this.genderReferentials = data[2] as Array<Referential>;
          this.jobPositionReferentials = data[3] as Array<Referential>;
          this.personalDataForm = this.createPersonalDataForm();

          this.employeeService.getManager(this.employee.managerId)
            .subscribe(
              data => {
                this.manager = data;
                this.employeeDataForm = this.createEmployeeDataForm();
                this.employeeDataForm = this.populateEmployeeDataForm();
              }
            )
        });
    } else {
      this.router.navigate(['/employees']);
    }
  }

  ngAfterViewInit(): void {
    if (this.tokenStorageService.getUser().roles.filter(role => role === 'Admin').length === 0) {
      this.employeeService.getEmployee(this.tokenStorageService.getUser().username).subscribe(data => {
        setTimeout(() => {
          $('#department').selectpicker();
          $('#department').selectpicker('val', data.department?.label);
          $('#department').selectpicker('refresh');
          $('#gender').selectpicker();
          $('#gender').selectpicker('val', data.gender?.label);
          $('#gender').selectpicker('refresh');
          if ($('#gender').val() === '') {
            this.personalDataForm.markAsPending();
          }
          $('#jobPosition').selectpicker();
          $('#jobPosition').selectpicker('val', data.jobPosition?.label);
          $('#jobPosition').selectpicker('refresh');
        }, 100);
      });
    }
  }

  createPersonalDataForm(): FormGroup {
    this.personalDataForm = this.formBuilder.group({
      'lastName': [this.employee.lastName, [Validators.required, Validators.maxLength(30)]],
      'firstName': [this.employee.firstName, [Validators.required, Validators.maxLength(30)]],
      'gender': [this.employee.gender ? this.employee.gender.label : '', [Validators.required]],
      'birthPlace': [this.employee.birthPlace, [Validators.required, Validators.maxLength(13)]],
      'personIdentifier': [this.employee.personIdentifier, [Validators.required, Validators.minLength(13), Validators.maxLength(13), WorkdayValidators.validPesonalIdentifier]],
      'birthDate': [this.employee.birthDate ? formatDate(this.employee.birthDate) : '', [Validators.required, WorkdayValidators.validDate]],
      'birthName': [this.employee.birthName, [Validators.required, Validators.maxLength(40)]],
      'email': [this.employee.email, [Validators.required, Validators.maxLength(40), WorkdayValidators.validEmail]],
      'homePhoneNumber': [this.employee.homePhoneNumber, [WorkdayValidators.validPhoneNumber]],
      'mobilePhoneNumber': [this.employee.mobilePhoneNumber, [Validators.required, Validators.maxLength(10), Validators.minLength(10), WorkdayValidators.validPhoneNumber]],
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
      'joiningDate': [this.employee.joiningDate ? formatDate(this.employee.joiningDate) : '', [Validators.required]],
      'currentPositionStartingDate': [this.employee.currentPositionStartingDate ? formatDate(this.employee.currentPositionStartingDate) : '', [Validators.required]],
      'manager': [this.manager.lastName + ' ' + this.manager.firstName, [Validators.required]],
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
    this.employee.birthDate = parseDate(this.personalDataForm.controls.birthDate.value);
    this.employee.birthName = this.personalDataForm.controls.birthName.value;
    this.employee.email = this.personalDataForm.controls.email.value;
    this.employee.homePhoneNumber = this.personalDataForm.controls.homePhoneNumber.value;
    this.employee.mobilePhoneNumber = this.personalDataForm.controls.mobilePhoneNumber.value;

    this.employeeService.putEmployee(this.employee).subscribe(data => {
      this.employeeService.setStoredEmployee(data);
      this.notificationService.showNotification('top','center', 'success', 'Datele au fost salvate cu succes.');
      this.personalDataForm.markAsPristine();
    });
  }

  putEmployeeData() {
    if (!this.employee.jobPosition) {
      this.employee.jobPosition = new Referential();
    }
    this.employee.jobPosition.label = this.employeeDataForm.controls.jobPosition.value;
    this.employee.entity = this.employeeDataForm.controls.entity.value;
    this.employee.location = this.employeeDataForm.controls.location.value;
    if (!this.employee.department) {
      this.employee.department = new Referential();
    }
    this.employee.department.label = this.employeeDataForm.controls.department.value;
    this.employee.ITDeduction = this.employeeDataForm.controls.ITDeduction.value;
    this.employee.joiningDate = parseDate(this.employeeDataForm.controls.joiningDate.value);
    this.employee.currentPositionStartingDate = parseDate(this.employeeDataForm.controls.currentPositionStartingDate.value);

    this.employeeService.putEmployee(this.employee).subscribe(data => {
      this.employeeService.setStoredEmployee(data);
    });
  }

}
