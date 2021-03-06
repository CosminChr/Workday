import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../../shared/models/employee.model";
import {Referential} from "../../../shared/models/referential.model";
import {Citizenship} from "../../../shared/models/citizenship.model";
import {BankAccount} from "../../../shared/models/bank-account.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EmployeeService} from "../../../shared/services/employee/employee.service";
import {forkJoin, Observable} from "rxjs";
import {CitizenshipService} from "./citizenship.service";
import {NationalityService} from "./nationality.service";
import {CitizenshipReferentialService} from "./citizenship-referential.service";
import {NationalityReferentialService} from "./nationality-referential.service";
import {formatDate} from "../../../shared/utils/utils";
import {Language} from "../../../shared/models/language.model";
import {LanguageService} from "./language.service";
import {LanguageReferentialService} from "./language-referential.service";
import {LanguageLevelReferentialService} from "./language-level-referential.service";
import {NotificationService} from "../../../shared/services/notification/notification.service";

declare var $: any;

@Component({
  selector: 'workday-citizenship',
  templateUrl: './citizenship.component.html',
  styleUrls: ['./citizenship.component.scss']
})
export class CitizenshipComponent implements OnInit, AfterViewInit {

  employee: Employee;

  citizenships: Array<Citizenship>;

  nationalityReferentials: Array<Referential>;

  citizenshipReferentials: Array<Referential>;

  nationality: Referential;

  newCitizenship = new Citizenship();

  nationalityFormGroup: FormGroup;

  citizenshipFormGroups: Array<FormGroup>;

  citizenshipFormGroup: FormGroup;

  languages: Array<Language>;

  newLanguage = new Language();

  languageFormGroups: Array<FormGroup>;

  languageFormGroup: FormGroup;

  languageLevelReferentials: Array<Referential>;

  languageReferentials: Array<Referential>;

  citizenshipDocuments: Array<any>;

  citizenshipDocument: any;

  languageCertifications: Array<any>;

  languageCertification: any;


  constructor(private employeeService: EmployeeService,
              private citizenshipService: CitizenshipService,
              private nationalityService: NationalityService,
              private citizenshipReferentialService: CitizenshipReferentialService,
              private nationalityReferentialService: NationalityReferentialService,
              private languageService : LanguageService,
              private languageReferentialService: LanguageReferentialService,
              private languageLevelReferentialService: LanguageLevelReferentialService,
              private notificationService: NotificationService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();

    forkJoin([
      this.citizenshipService.getCitizenships(this.employee.id),
      this.nationalityService.getnationality(this.employee.id),
      this.citizenshipReferentialService.getCitizenshipReferentials(),
      this.nationalityReferentialService.getNationalityReferentials(),
      this.languageService.getLanguages(this.employee.id),
      this.languageReferentialService.getLanguageReferentials(),
      this.languageLevelReferentialService.getLanguageLevelReferentials()
    ]).subscribe(data => {
      this.citizenships = data[0] as Array<Citizenship>;
      this.nationality = data[1] as Referential;
      this.citizenshipReferentials = data[2] as Array<Referential>;
      this.nationalityReferentials = data[3] as Array<Referential>;
      this.languages = data[4] as Array<Language>;
      this.languageReferentials = data[5] as Array<Referential>;
      this.languageLevelReferentials = data[6] as Array<Referential>;
      this.createNationalityFormGroup();
      this.createCitizenshipFormGroups();
      this.createNewCitizenshipForm();
      this.createLanguageFormGroups();
      this.createNewLanguageForm();
      this.citizenshipDocuments = new Array<any>(this.citizenships.length);
      this.languageCertifications = new Array<any>(this.languages.length);
    });
  }

  ngAfterViewInit(): void {
    this.citizenshipService.getCitizenships(this.employee.id)
      .subscribe( (data: Array<Citizenship>) => {
        setTimeout( () => {
          $('.selectpicker').selectpicker();
          for (let i = 0; i < data.length; i++) {
            $('#' + i).selectpicker('val', data[i]?.citizenship.label);
            $('#' + i).selectpicker('refresh');
          }
        }, 500);
    });
    this.languageService.getLanguages(this.employee.id)
      .subscribe( (data: Array<Language>) =>{
        setTimeout( () => {
          for (let i = 0; i < data.length; i++) {
            $('#language-' +i).selectpicker('val', data[i]?.language.label);
            $('#language-' +i).selectpicker('refresh');
            $('#reading-' +i).selectpicker('val', data[i]?.reading.label);
            $('#reading-' +i).selectpicker('refresh');
            $('#writing-' +i).selectpicker('val', data[i]?.writing.label);
            $('#writing-' +i).selectpicker('refresh');
            $('#speaking-' +i).selectpicker('val', data[i]?.speaking.label);
            $('#speaking-' +i).selectpicker('refresh');
            $('#overallLevel-' +i).selectpicker('val', data[i]?.overallLevel.label);
            $('#overallLevel-' +i).selectpicker('refresh');
            if ($('#language-' + i).val() === ''
              || $('#reading-' + i).val() === ''
              || $('#writing-' + i).val() === ''
              || $('#speaking-' + i).val() === ''
              || $('#overallLevel-' + i).val() === '') {
              this.languageFormGroups[i].markAsPending();
            }
          }
        }, 500);

      });
  }

  createNationalityFormGroup(): FormGroup {

    this.nationalityFormGroup = this.formBuilder.group({
      'nationality': [this.nationality?.label, [Validators.required, Validators.maxLength(100)]],
    });
    return this.nationalityFormGroup;
  }

  createCitizenshipFormGroups(): Array<FormGroup> {
    if (this.citizenships) {
      this.citizenshipFormGroups = new Array<FormGroup>(this.citizenships.length);
      for (let i = 0; i < this.citizenships.length; i++) {
        this.citizenshipFormGroups[i] = this.formBuilder.group({
          'citizenship': [this.citizenships[i]?.citizenship.label, [Validators.required, Validators.maxLength(100)]],
        });
      }
    }
    return this.citizenshipFormGroups;
  }

  reinitializePicker() {
    $('.selectpicker').selectpicker('refresh');
  }

  createNewCitizenshipForm(): FormGroup {
    this.newCitizenship.citizenship = new Referential();

    this.citizenshipFormGroup = this.formBuilder.group({
      'citizenship': [this.newCitizenship.citizenship.label, [Validators.required, Validators.maxLength(100)]]
    });

    return this.citizenshipFormGroup;
  }


  putCitizenship(index: number) {
    this.citizenships[index].citizenship.label = this.citizenshipFormGroups[index].controls.citizenship.value;

    const data = new FormData();
    data.append("citizenshipCertificate", this.citizenshipDocument ? this.citizenshipDocument: new Blob(), this.citizenshipDocument?.name);
    data.append('citizenship', new Blob([JSON.stringify(this.citizenships[index])], {
      type: "application/json"
    }));

    this.citizenshipService.putCitizenship(data).subscribe( (data: Citizenship) => {
      this.citizenshipService.getCitizenships(this.employee.id).subscribe( data => {
        this.citizenshipFormGroups[index].markAsPristine();
        this.notificationService.showNotification('top', 'center', 'success', 'Datele au fost modificate cu succes.');

        this.citizenships = data;
        this.createCitizenshipFormGroups();
        setTimeout(() => {
          $('.selectpicker').selectpicker();
          for (let i = 0; i <  this.citizenships.length; i++) {
            $('#' + i).selectpicker('val',  this.citizenships[i]?.citizenship.label);
            $('#' + i).selectpicker('refresh');
          }
        }, 500);
      })
    });
  }

  putNewCitizenship(){
    this.newCitizenship.citizenship.label = this.citizenshipFormGroup.controls.citizenship.value;
    this.newCitizenship.employee = this.employee;

    const data = new FormData();
    data.append("citizenshipCertificate", this.citizenshipDocument ? this.citizenshipDocument: new Blob(), this.citizenshipDocument?.name);
    data.append('citizenship', new Blob([JSON.stringify(this.newCitizenship)], {
      type: "application/json"
    }));

    this.citizenshipService.putCitizenship(data).subscribe( (data: Citizenship) => {
      this.notificationService.showNotification('top', 'center', 'success', 'Datele au fost salvate cu succes.');
        this.citizenships.push(data);
      this.createCitizenshipFormGroups();
      setTimeout(() => {
        $('.selectpicker').selectpicker();
        for (let i = 0; i <  this.citizenships.length; i++) {
          $('#' + i).selectpicker('val',  this.citizenships[i]?.citizenship.label);
          $('#' + i).selectpicker('refresh');
        }
      }, 500);
    });
  }

  createLanguageFormGroups(): Array<FormGroup> {
    if (this.languages){
      this.languageFormGroups = new Array<FormGroup>(this.languages.length);
      for (let i = 0; i < this.languages.length; i++) {
        this.languageFormGroups[i] = this.formBuilder.group({
          'language': [this.languages[i]?.language.label, [Validators.required, Validators.maxLength(100)]],
          'reading': [this.languages[i]?.reading.label, [Validators.required, Validators.maxLength(100)]],
          'writing': [this.languages[i]?.writing.label, [Validators.required, Validators.maxLength(100)]],
          'speaking': [this.languages[i]?.speaking.label, [Validators.required, Validators.maxLength(100)]],
          'overallLevel': [this.languages[i]?.overallLevel.label, [Validators.required, Validators.maxLength(100)]]
        });
      }
    }
    return this.citizenshipFormGroups;
  }

  createNewLanguageForm(): FormGroup {
    this.newLanguage.language = new Referential();
    this.newLanguage.reading = new Referential();
    this.newLanguage.writing = new Referential();
    this.newLanguage.speaking = new Referential();
    this.newLanguage.overallLevel = new Referential();


    this.languageFormGroup = this.formBuilder.group({
      'language': [this.newLanguage.language.label, [Validators.required, Validators.maxLength(100)]],
      'reading': [this.newLanguage.reading.label, [Validators.required, Validators.maxLength(100)]],
      'writing': [this.newLanguage.writing.label, [Validators.required, Validators.maxLength(100)]],
      'speaking': [this.newLanguage.speaking.label, [Validators.required, Validators.maxLength(100)]],
      'overallLevel': [this.newLanguage.overallLevel.label, [Validators.required, Validators.maxLength(100)]]
    });

    return this.languageFormGroup;
  }

  putLanguage(index: number){
    this.languages[index].language.label = this.languageFormGroups[index].controls.language.value;
    this.languages[index].reading.label = this.languageFormGroups[index].controls.reading.value;
    this.languages[index].writing.label = this.languageFormGroups[index].controls.writing.value;
    this.languages[index].speaking.label = this.languageFormGroups[index].controls.speaking.value;
    this.languages[index].overallLevel.label = this.languageFormGroups[index].controls.overallLevel.value;

    const data = new FormData();
    data.append("languageCertification", this.languageCertification ? this.languageCertification: new Blob(), this.languageCertification?.name);
    data.append('language', new Blob([JSON.stringify(this.languages[index])], {
      type: "application/json"
    }));

    this.languageService.putLanguage(data).subscribe( (data: Language) => {
      this.languageService.getLanguages(this.employee.id).subscribe( data => {
        this.notificationService.showNotification('top', 'center', 'success', 'Datele au fost salvate cu succes.');
        this.languages = data;
        this.createLanguageFormGroups();
        setTimeout( () => {
          for (let i = 0; i < this.languages.length; i++) {
            $('#language-' +i).selectpicker('val', this.languages[i]?.language.label);
            $('#language-' +i).selectpicker('refresh');
            $('#reading-' +i).selectpicker('val', this.languages[i]?.reading.label);
            $('#reading-' +i).selectpicker('refresh');
            $('#writing-' +i).selectpicker('val', this.languages[i]?.writing.label);
            $('#writing-' +i).selectpicker('refresh');
            $('#speaking-' +i).selectpicker('val', this.languages[i]?.speaking.label);
            $('#speaking-' +i).selectpicker('refresh');
            $('#overallLevel-' +i).selectpicker('val', this.languages[i]?.overallLevel.label);
            $('#overallLevel-' +i).selectpicker('refresh');

            if ($('#language-' + i).val() === ''
              || $('#reading-' + i).val() === ''
              || $('#writing-' + i).val() === ''
              || $('#speaking-' + i).val() === ''
              || $('#overallLevel-' + i).val() === '') {
              this.languageFormGroups[i].markAsPending();
            }
          }
        }, 500);
      })
    });
  }

  putNewLanguage(){
    this.newLanguage.language.label = this.languageFormGroup.controls.language.value;
    this.newLanguage.reading.label = this.languageFormGroup.controls.reading.value;
    this.newLanguage.writing.label = this.languageFormGroup.controls.writing.value;
    this.newLanguage.speaking.label = this.languageFormGroup.controls.speaking.value;
    this.newLanguage.overallLevel.label = this.languageFormGroup.controls.overallLevel.value;
    this.newLanguage.employee = this.employee;

    const data = new FormData();
    data.append("languageCertification", this.languageCertification ? this.languageCertification: new Blob(), this.languageCertification?.name);
    data.append('language', new Blob([JSON.stringify(this.newLanguage)], {
      type: "application/json"
    }));

    this.languageService.putLanguage(data).subscribe( (data: Language) => {
      this.notificationService.showNotification('top', 'center', 'success', 'Datele au fost salvate cu succes.');
      this.languages.push(data);
      this.createLanguageFormGroups();
      setTimeout( () => {
        for (let i = 0; i < this.languages.length; i++) {
          $('#language-' +i).selectpicker('val', this.languages[i]?.language.label);
          $('#language-' +i).selectpicker('refresh');
          $('#reading-' +i).selectpicker('val', this.languages[i]?.reading.label);
          $('#reading-' +i).selectpicker('refresh');
          $('#writing-' +i).selectpicker('val', this.languages[i]?.writing.label);
          $('#writing-' +i).selectpicker('refresh');
          $('#speaking-' +i).selectpicker('val', this.languages[i]?.speaking.label);
          $('#speaking-' +i).selectpicker('refresh');
          $('#overallLevel-' +i).selectpicker('val', this.languages[i]?.overallLevel.label);
          $('#overallLevel-' +i).selectpicker('refresh');

          if ($('#language-' + i).val() === ''
            || $('#reading-' + i).val() === ''
            || $('#writing-' + i).val() === ''
            || $('#speaking-' + i).val() === ''
            || $('#overallLevel-' + i).val() === '') {
            this.languageFormGroups[i].markAsPending();
          }
        }
      }, 500);

    });
  }

  uploadCitizenshipFile(event, index) {
    this.citizenshipDocuments[index] = event.target.files[0];
  }

  uploadNewCitizenshipFile(event) {
    this.citizenshipDocument = event.target.files[0];
  }

  uploadLanguageCertificateFile(event, index) {
    this.languageCertifications[index] = event.target.files[0];
  }

  uploadNewLanguageCertificateFile(event) {
    this.languageCertification = event.target.files[0];
  }
}




