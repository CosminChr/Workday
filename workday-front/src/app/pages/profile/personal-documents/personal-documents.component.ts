import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../../shared/models/employee.model";
import {Referential} from "../../../shared/models/referential.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {IdentityDocument} from "../../../shared/models/identity-document.model";
import {EmployeeService} from "../../../shared/services/employee/employee.service";
import {forkJoin} from "rxjs";
import {IdentityDocumentService} from "./identity-document.service";
import {IdentityDocumentReferentialService} from "./identity-document-referential.service";
import {CountryReferentialService} from "./country-referential.service";
import {formatDate, parseDate} from "../../../shared/utils/utils";

declare var $: any;

@Component({
  selector: 'workday-personal-documents',
  templateUrl: './personal-documents.component.html',
  styleUrls: ['./personal-documents.component.scss']
})
export class PersonalDocumentsComponent implements OnInit, AfterViewInit {

  employee: Employee;

  identityDocumentTypeReferentials: Array<Referential>;

  countryReferentials: Array<Referential>;

  identityDocuments: Array<IdentityDocument>;

  newIdentityDocument = new IdentityDocument();

  isDoesAnyIdentityDocumentExist = true;

  identityDocumentFormGroups: Array<FormGroup>;

  identityDocumentFormGroup: FormGroup;

  constructor(private employeeService: EmployeeService,
              private identityDocumentReferentialService: IdentityDocumentReferentialService,
              private identityDocumentService: IdentityDocumentService,
              private countryReferentialService: CountryReferentialService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();

    forkJoin([
      this.identityDocumentReferentialService.getIdentityDocumentTypeReferentials(),
      this.identityDocumentService.getIdentityDocuments(this.employee.id),
      this.countryReferentialService.getCountryReferentials(),
    ])
      .subscribe(data => {
        this.identityDocumentTypeReferentials = data[0] as Array<Referential>;
        this.identityDocuments = data[1] as Array<IdentityDocument>;
        this.countryReferentials = data[2] as Array<Referential>;
        this.createIdentityDocumentForms();
        this.createNewIdentityDocumentForm();
      });
  }

  createIdentityDocumentForms(): Array<FormGroup> {
    if (this.identityDocuments) {
      this.identityDocumentFormGroups = new Array<FormGroup>(this.identityDocuments.length);
      for (let i = 0; i < this.identityDocuments.length; i++) {
        this.identityDocumentFormGroups[i] = this.formBuilder.group({
          'identityDocumentType': [this.identityDocuments[i]?.identityDocumentType.label, [Validators.required, Validators.maxLength(100)]],
          'seriesAndNumber': [this.identityDocuments[i].seriesAndNumber, [Validators.required, Validators.maxLength(100)]],
          'issueDate': [this.identityDocuments[i].issueDate ? formatDate(this.identityDocuments[i].issueDate) : '', [Validators.required]],
          'expirationDate': [this.identityDocuments[i].expirationDate ? formatDate(this.identityDocuments[i].expirationDate) : '', [Validators.required]],
          'issuer': [this.identityDocuments[i].issuer, [Validators.required, Validators.maxLength(100)]],
          'country': [this.identityDocuments[i]?.country.label, [Validators.required, Validators.maxLength(100)]],
        });
      }
    } else {
      this.isDoesAnyIdentityDocumentExist = false;
    }
    return this.identityDocumentFormGroups;
  }

  createNewIdentityDocumentForm(): FormGroup {
    this.newIdentityDocument.identityDocumentType = new Referential();
    this.newIdentityDocument.country = new Referential();

    this.identityDocumentFormGroup = this.formBuilder.group({
      'identityDocumentType': [this.newIdentityDocument.identityDocumentType.label, [Validators.required, Validators.maxLength(100)]],
      'seriesAndNumber': [this.newIdentityDocument.seriesAndNumber, [Validators.required, Validators.maxLength(100)]],
      'issueDate': [this.newIdentityDocument.issueDate ? formatDate(this.newIdentityDocument.issueDate) : '', [Validators.required, Validators.maxLength(100)]],
      'expirationDate': [this.newIdentityDocument.expirationDate ? formatDate(this.newIdentityDocument.issueDate) : '', [Validators.required]],
      'issuer': [this.newIdentityDocument.issuer, [Validators.required, Validators.maxLength(100)]],
      'country': [this.newIdentityDocument.country.label, [Validators.required, Validators.maxLength(100)]],
    });

    return this.identityDocumentFormGroup;
  }

  putNewIdentityDocument() {

    this.newIdentityDocument.identityDocumentType.label = this.identityDocumentFormGroup.controls.identityDocumentType.value;
    this.newIdentityDocument.seriesAndNumber = this.identityDocumentFormGroup.controls.seriesAndNumber.value;
    this.newIdentityDocument.issueDate = parseDate(this.identityDocumentFormGroup.controls.issueDate.value);
    this.newIdentityDocument.expirationDate = parseDate(this.identityDocumentFormGroup.controls.expirationDate.value);
    this.newIdentityDocument.issuer = this.identityDocumentFormGroup.controls.issuer.value;
    this.newIdentityDocument.country.label = this.identityDocumentFormGroup.controls.country.value;


    this.newIdentityDocument.employee = this.employee;
    this.identityDocumentService.putIdentityDocument(this.newIdentityDocument).subscribe(data => {
      this.identityDocuments.push(data as IdentityDocument);
    });

  }

  ngAfterViewInit(): void {
    setTimeout(function () {
      $('.selectpicker').selectpicker();
    }, 500);
  }

  reinitializePicker() {
    $('.selectpicker').selectpicker('refresh');
  }

}
