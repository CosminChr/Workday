import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../../shared/models/employee.model";
import {Referential} from "../../../shared/models/referential.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AcademicStudy} from "../../../shared/models/academic-study.model";
import {EmployeeService} from "../../../shared/services/employee/employee.service";
import {CountryReferentialService} from "../personal-documents/country-referential.service";
import {StudyLevelReferentialService} from "./study-level-referential.service";
import {AcademicStudyService} from "./academic-study.service";
import {forkJoin} from "rxjs";
import {formatDate, parseDate} from "../../../shared/utils/utils";
import {StudyFieldReferentialService} from "./study-field-referential.service";
import {WorkdayValidators} from "../../../shared/validators/workday-validators";
import {NotificationService} from "../../../shared/services/notification/notification.service";

declare var $: any;

@Component({
  selector: 'workday-studies',
  templateUrl: './studies.component.html',
  styleUrls: ['./studies.component.scss']
})
export class StudiesComponent implements OnInit, AfterViewInit {

  employee: Employee;

  studyLevelReferentials: Array<Referential>;

  studyFieldReferentials: Array<Referential>;

  countryReferentials: Array<Referential>;

  academicStudies: Array<AcademicStudy>;

  newAcademicStudy = new AcademicStudy();

  academicStudyFormGroups: Array<FormGroup>;

  academicStudyFormGroup: FormGroup;

  diplomas: Array<any>;

  diploma: any;

  constructor(private studyLevelReferentialService: StudyLevelReferentialService,
              private academicStudyService: AcademicStudyService,
              private countryReferentialService: CountryReferentialService,
              private employeeService: EmployeeService,
              private studyFieldReferentialService: StudyFieldReferentialService,
              private notificationService: NotificationService,
              private formBuilder: FormBuilder) {
  }


  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();

    forkJoin([
      this.countryReferentialService.getCountryReferentials(),
      this.academicStudyService.getStudies(this.employee.id),
      this.studyLevelReferentialService.getStudyLevelReferentials(),
      this.studyFieldReferentialService.getStudyFieldReferentials()
    ])
      .subscribe(data => {
        this.countryReferentials = data[0];
        this.academicStudies = data[1];
        this.studyLevelReferentials = data[2];
        this.studyFieldReferentials = data[3];
        this.creatAcademicStudyForms();
        this.createNewStudyForm();

      });
  }

  ngAfterViewInit(): void {

    this.academicStudyService.getStudies(this.employee.id)
      .subscribe((data: Array<AcademicStudy>) => {
        setTimeout( () => {
          $('.selectpicker').selectpicker();
          for (let i = 0; i < data.length; i++) {
            $('#studyLevel-' + i).selectpicker('val', data[i].studyLevel.label);
            $('#studyLevel-' + i).selectpicker('refresh');

            $('#studyField-' + i).selectpicker('val', data[i].studyField.label);
            $('#studyField-' + i).selectpicker('refresh');

            $('#country-' + i).selectpicker('val', data[i].country.label);
            $('#country-' + i).selectpicker('refresh');

            if ($('#studyLevel-' + i).val() === '' || $('#studyField-' + i).val() === '' || $('#country-' + i).val() === '') {
              this.academicStudyFormGroups[i].markAsPending();
            }
          }

        }, 500);
      });

  }

  creatAcademicStudyForms(): Array<FormGroup> {
    if (this.academicStudies) {
      this.academicStudyFormGroups = new Array<FormGroup>(this.academicStudies.length);
      for (let i = 0; i < this.academicStudies.length; i++) {
        this.academicStudyFormGroups[i] = this.formBuilder.group({
          'studyLevel': [this.academicStudies[i].studyLevel.label, [Validators.required, Validators.maxLength(100)]],
          'educationalInstitution': [this.academicStudies[i].educationalInstitution, [Validators.required, Validators.maxLength(100)]],
          'studyField': [this.academicStudies[i].studyField.label, [Validators.required, Validators.maxLength(100)]],
          'specialization': [this.academicStudies[i].specialization, [Validators.required, Validators.maxLength(100)]],
          'country': [this.academicStudies[i].country.label, [Validators.required, Validators.maxLength(100)]],
          'fromDate': [this.academicStudies[i].fromDate ? formatDate(this.academicStudies[i].fromDate) : '', [Validators.required, WorkdayValidators.validDate]],
          'toDate': [this.academicStudies[i].toDate ? formatDate(this.academicStudies[i].toDate) : '', [Validators.required, WorkdayValidators.validDate]],
          'finalized': [this.academicStudies[i].finalized]
        });
      }
    }

    return this.academicStudyFormGroups;
  }

  reinitializePicker() {
    $('.selectpicker').selectpicker('refresh');
  }

  createNewStudyForm(): FormGroup {
    this.newAcademicStudy.studyLevel = new Referential();
    this.newAcademicStudy.studyField = new Referential();
    this.newAcademicStudy.country = new Referential();

    this.academicStudyFormGroup = this.formBuilder.group({
      'studyLevel': [this.newAcademicStudy.studyLevel.label, [Validators.required, Validators.maxLength(100)]],
      'educationalInstitution': [this.newAcademicStudy.educationalInstitution, [Validators.required, Validators.maxLength(100)]],
      'studyField': [this.newAcademicStudy.studyField.label, [Validators.required, Validators.maxLength(100)]],
      'specialization': [this.newAcademicStudy.specialization, [Validators.required, Validators.maxLength(100)]],
      'country': [this.newAcademicStudy.country.label, [Validators.required, Validators.maxLength(100)]],
      'fromDate': [this.newAcademicStudy.fromDate ? formatDate(this.newAcademicStudy.fromDate) : '', [Validators.required, WorkdayValidators.validDate]],
      'toDate': [this.newAcademicStudy.toDate ? formatDate(this.newAcademicStudy.toDate) : '', [Validators.required, WorkdayValidators.validDate]],
      'finalized': [this.newAcademicStudy.finalized]
    });

    return this.academicStudyFormGroup;
  }

  putStudy(index: number) {

    this.academicStudies[index].studyLevel.label = this.academicStudyFormGroups[index].controls.studyLevel.value;
    this.academicStudies[index].educationalInstitution = this.academicStudyFormGroups[index].controls.educationalInstitution.value;
    this.academicStudies[index].studyField.label = this.academicStudyFormGroups[index].controls.studyField.value;
    this.academicStudies[index].specialization = this.academicStudyFormGroups[index].controls.specialization.value;
    this.academicStudies[index].country.label = this.academicStudyFormGroups[index].controls.country.value;
    this.academicStudies[index].fromDate = parseDate(this.academicStudyFormGroups[index].controls.fromDate.value);
    this.academicStudies[index].toDate = parseDate(this.academicStudyFormGroups[index].controls.toDate.value);
    this.academicStudies[index].finalized = this.academicStudyFormGroups[index].controls.finalized.value;

    const data = new FormData();
    data.append("diploma", this.diploma ? this.diploma: new Blob(), this.diploma?.name);
    data.append('academicStudy', new Blob([JSON.stringify(this.academicStudies[index])], {
      type: "application/json"
    }));

    this.academicStudyService.putStudy(data).subscribe((data: AcademicStudy) => {
      this.academicStudyService.getStudies(this.employee.id).subscribe( data => {
        this.notificationService.showNotification('top', 'center', 'success', 'Datele au fost modificate cu succes.');
        this.academicStudyFormGroups[index].markAsPristine();
        this.academicStudies = data;
        this.creatAcademicStudyForms();
        setTimeout( () => {
          $('.selectpicker').selectpicker();
          for (let i = 0; i <  this.academicStudies.length; i++) {
            $('#studyLevel-' + i).selectpicker('val',  this.academicStudies[i].studyLevel.label);
            $('#studyLevel-' + i).selectpicker('refresh');

            $('#studyField-' + i).selectpicker('val',  this.academicStudies[i].studyField.label);
            $('#studyField-' + i).selectpicker('refresh');

            $('#country-' + i).selectpicker('val',  this.academicStudies[i].country.label);
            $('#country-' + i).selectpicker('refresh');

            if ($('#studyLevel-' + i).val() === '' || $('#studyField-' + i).val() === '' || $('#country-' + i).val() === '') {
              this.academicStudyFormGroups[i].markAsPending();
            }
          }
        }, 500);
      });
    });
  }

  putNewStudy() {
    this.newAcademicStudy.studyLevel.label = this.academicStudyFormGroup.controls.studyLevel.value;
    this.newAcademicStudy.educationalInstitution = this.academicStudyFormGroup.controls.educationalInstitution.value;
    this.newAcademicStudy.studyField.label = this.academicStudyFormGroup.controls.studyField.value;
    this.newAcademicStudy.specialization = this.academicStudyFormGroup.controls.specialization.value;
    this.newAcademicStudy.country.label = this.academicStudyFormGroup.controls.country.value;
    this.newAcademicStudy.fromDate = parseDate(this.academicStudyFormGroup.controls.fromDate.value);
    this.newAcademicStudy.toDate = parseDate(this.academicStudyFormGroup.controls.toDate.value);
    this.newAcademicStudy.finalized = this.academicStudyFormGroup.controls.finalized.value;
    this.newAcademicStudy.employee = this.employee;

    const data = new FormData();
    data.append("diploma", this.diploma ? this.diploma: new Blob(), this.diploma?.name);
    data.append('academicStudy', new Blob([JSON.stringify(this.newAcademicStudy)], {
      type: "application/json"
    }));

    this.academicStudyService.putStudy(data).subscribe((data: AcademicStudy) => {
      this.notificationService.showNotification('top', 'center', 'success', 'Datele au fost modificate cu succes.');
      this.academicStudies.push(data);
      this.creatAcademicStudyForms();
      setTimeout( () => {
        $('.selectpicker').selectpicker();
        for (let i = 0; i <  this.academicStudies.length; i++) {
          $('#studyLevel-' + i).selectpicker('val',  this.academicStudies[i].studyLevel.label);
          $('#studyLevel-' + i).selectpicker('refresh');

          $('#studyField-' + i).selectpicker('val',  this.academicStudies[i].studyField.label);
          $('#studyField-' + i).selectpicker('refresh');

          $('#country-' + i).selectpicker('val',  this.academicStudies[i].country.label);
          $('#country-' + i).selectpicker('refresh');

          if ($('#studyLevel-' + i).val() === '' || $('#studyField-' + i).val() === '' || $('#country-' + i).val() === '') {
            this.academicStudyFormGroups[i].markAsPending();
          }
        }
      }, 500);
    });
  }

  uploadDiplomaFile(event, index) {
    this.diplomas[index] = event.target.files[0];
  }

  uploadNewDiplomaFile(event) {
    this.diploma = event.target.files[0];
  }

}
