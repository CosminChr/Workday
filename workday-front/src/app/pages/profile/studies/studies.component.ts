import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../../shared/models/employee.model";
import {Referential} from "../../../shared/models/referential.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AcademicStudy} from "../../../shared/models/academic-study.model";
import {EmployeeService} from "../../../shared/services/employee/employee.service";
import {BankAccountService} from "../bank-account/bank-account.service";
import {CurrencyReferentialService} from "../bank-account/currency-referential.service";
import {CountryReferentialService} from "../personal-documents/country-referential.service";
import {StudyLevelReferentialService} from "./study-level-referential.service";
import {AcademicStudyService} from "./academic-study.service";
import {forkJoin} from "rxjs";
import {formatDate, parseDate} from "../../../shared/utils/utils";
import {StudyFieldReferentialService} from "./study-field-referential.service";
import {Citizenship} from "../../../shared/models/citizenship.model";
import {Language} from "../../../shared/models/language.model";

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

  constructor(private studyLevelReferentialService: StudyLevelReferentialService,
              private academicStudyService: AcademicStudyService,
              private countryReferentialService: CountryReferentialService,
              private employeeService: EmployeeService,
              private studyFieldReferentialService: StudyFieldReferentialService,
              private formBuilder: FormBuilder) { }


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
      .subscribe( (data: Array<AcademicStudy>) => {
        setTimeout(function () {
          $('.selectpicker').selectpicker();
          for (let i = 0; i < data.length; i++) {
            $('#studyLevel-' + i).selectpicker('val', data[i].studyLevel.label);
            $('#studyLevel-' + i).selectpicker('refresh');

            $('#studyField-' + i).selectpicker('val', data[i].studyField.label);
            $('#studyField-' + i).selectpicker('refresh');

            $('#country-' + i).selectpicker('val', data[i].country.label);
            $('#country-' + i).selectpicker('refresh');
          }

        }, 500);
      });

  }

  creatAcademicStudyForms(): Array<FormGroup> {
    if (this.academicStudies) {
      this.academicStudyFormGroups = new Array<FormGroup>(this.academicStudies.length);
      for (let i = 0; i < this.academicStudies.length; i++) {
        this.academicStudyFormGroups[i] = this.formBuilder.group({
          'studyLevel': [this.academicStudies[i].studyLevel, [Validators.required, Validators.maxLength(100)]],
          'educationalInstitution': [this.academicStudies[i].educationalInstitution, [Validators.required, Validators.maxLength(100)]],
          'studyField': [this.academicStudies[i].studyField, [Validators.required, Validators.maxLength(100)]],
          'specialization': [this.academicStudies[i].specialization, [Validators.required, Validators.maxLength(100)]],
          'country': [this.academicStudies[i].country, [Validators.required, Validators.maxLength(100)]],
          'fromDate': [this.academicStudies[i].fromDate ? formatDate(this.academicStudies[i].fromDate) : '', [Validators.required]],
          'toDate': [this.academicStudies[i].toDate ? formatDate(this.academicStudies[i].toDate) : '', [Validators.required]],
          'finalized': [this.academicStudies[i].finalized, [Validators.required, Validators.maxLength(100)]]
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
      'fromDate': [this.newAcademicStudy.fromDate ? formatDate(this.newAcademicStudy.fromDate) : '', [Validators.required]],
      'toDate': [this.newAcademicStudy.toDate ? formatDate(this.newAcademicStudy.toDate) : '', [Validators.required]],
      'finalized': [this.newAcademicStudy.finalized, [Validators.required, Validators.maxLength(100)]]
    });

    return this.academicStudyFormGroup;
  }

  putNewStudy(){
    this.newAcademicStudy.studyLevel.label = this.academicStudyFormGroup.controls.studyLevel.value;
    this.newAcademicStudy.educationalInstitution= this.academicStudyFormGroup.controls.educationalInstitution.value;
    this.newAcademicStudy.studyField.label = this.academicStudyFormGroup.controls.studyField.value;
    this.newAcademicStudy.specialization = this.academicStudyFormGroup.controls.specialization.value;
    this.newAcademicStudy.country.label = this.academicStudyFormGroup.controls.country.value;
    this.newAcademicStudy.fromDate = parseDate(this.academicStudyFormGroup.controls.fromDate.value);
    this.newAcademicStudy.toDate = parseDate(this.academicStudyFormGroup.controls.toDate.value);
    this.newAcademicStudy.finalized = this.academicStudyFormGroup.controls.finalized.value;
    this.newAcademicStudy.employee=this.employee;

    this.academicStudyService.putStudy(this.newAcademicStudy).subscribe( (data: AcademicStudy) => {
      this.academicStudies.push(data);
    });
  }

}
