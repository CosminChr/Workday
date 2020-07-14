import {AfterViewInit, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Employee} from "../../../shared/models/employee.model";
import {Referential} from "../../../shared/models/referential.model";
import {MaritalStatus} from "../../../shared/models/marital-status.model";
import {Child} from "../../../shared/models/child.model";
import {Partner} from "../../../shared/models/partner.model";
import {EmployeeService} from "../../../shared/services/employee/employee.service";
import {forkJoin} from "rxjs";
import {MaritalStatusService} from "./marital-status.service";
import {MaritalStatusReferentialService} from "./marital-status-referential.service";
import {PartnerService} from "./partner.service";
import {formatDate, parseDate} from "../../../shared/utils/utils";
import {ChildService} from "./child.service";
import {GenderReferentialService} from "./gender-referential.service";
import {WorkdayValidators} from "../../../shared/validators/workday-validators";
import {NotificationService} from "../../../shared/services/notification/notification.service";

declare var $: any;

@Component({
  selector: 'workday-marital-status',
  templateUrl: './marital-status.component.html',
  styleUrls: ['./marital-status.component.scss']
})
export class MaritalStatusComponent implements OnInit, AfterViewInit {

  employee: Employee;

  partner: Partner;

  maritalStatusReferentials: Array<Referential>;

  maritalStatus: MaritalStatus;

  maritalStatusFormGroup: FormGroup;

  children: Array<Child>;

  newChild = new Child();

  childrenFormGroups: Array<FormGroup>;

  newChildFormGroup: FormGroup;

  isDoesAnyChildExist = true;

  genderReferentials: Array<Referential>;

  marriageCertificate: any;

  birthCertificates: Array<any>;

  birthCertificate: any;

  constructor(private employeeService: EmployeeService,
              private maritalStatusService: MaritalStatusService,
              private maritalStatusReferentialService: MaritalStatusReferentialService,
              private childService: ChildService,
              private partnerService: PartnerService,
              private genderReferentialService: GenderReferentialService,
              private notificationService: NotificationService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.employee = this.employeeService.getSavedEmployee();

    forkJoin([
      this.maritalStatusService.getMaritalStatus(this.employee.id),
      this.maritalStatusReferentialService.getMaritalStatusReferentials(),
      this.partnerService.getPartner(this.employee.id),
      this.childService.getChildren(this.employee.id),
      this.genderReferentialService.getGenderReferentials()
    ])
      .subscribe(data => {
        this.maritalStatus = data[0] as MaritalStatus;
        this.maritalStatusReferentials = data[1] as Array<Referential>;
        this.partner = data[2] as Partner;
        this.children = data[3] as Array<Child>;
        this.genderReferentials = data[4] as Array<Referential>;
        if (!this.partner) {
          this.partner = new Partner();
        }
        this.createMaritalStatusForm();
        this.createChildForms();
        this.createNewChildForm();
        this.onMaritalStatusChange();
        this.birthCertificates = new Array<any>(this.children.length);
      });
  }

  ngAfterViewInit(): void {
    this.maritalStatusService.getMaritalStatus(this.employee.id).subscribe(data => {
      setTimeout(() => {
        $('.selectpicker').selectpicker();
        $('.selectpicker').selectpicker('val', data.maritalStatus.label);
        $('.selectpicker').selectpicker('refresh');
        if ($('#maritalStatus').val() === '') {
          this.maritalStatusFormGroup.markAsPending();
        }
        if (this.maritalStatus.maritalStatus.label === 'Căsătorit(ă)') {
          this.maritalStatusFormGroup.get('startingDate').setErrors(null);
          this.maritalStatusFormGroup.get('lastName').setErrors(null);
          this.maritalStatusFormGroup.get('firstName').setErrors(null);
          this.maritalStatusFormGroup.get('personIdentifier').setErrors(null);
          this.maritalStatusFormGroup.get('birthDate').setErrors(null);
          this.maritalStatusFormGroup.get('worksInCompany').setErrors(null);
        }
      }, 100);
    });

    this.childService.getChildren(this.employee.id).subscribe(data => {
      setTimeout(() => {

        let children = data as Array<Child>;

        for (let i = 0; i < children.length; i++) {
          $('#' + i).selectpicker();
          $('#' + i).selectpicker('val', children[i].gender.label);
          $('#' + i).selectpicker('refresh');
          if ($('#' + i).val() === '') {
            this.childrenFormGroups[i].markAsPending();
          }
        }
      }, 200);
    });
  }

  reinitializePicker() {
    $('.selectpicker').selectpicker('refresh');
  }

  createMaritalStatusForm(): FormGroup {

    this.maritalStatusFormGroup = this.formBuilder.group({
      'maritalStatus': [this.maritalStatus?.maritalStatus.label, [Validators.required, Validators.maxLength(30)]],
      'startingDate': [this.maritalStatus.startingDate ? formatDate(this.maritalStatus.startingDate) : '', [Validators.required, WorkdayValidators.validDate]],
      'lastName': [this.partner.lastName, [Validators.required, Validators.maxLength(30)]],
      'firstName': [this.partner.firstName, [Validators.required, Validators.maxLength(30)]],
      'personIdentifier': [this.partner.personIdentifier, [Validators.required, Validators.minLength(13), Validators.maxLength(13), WorkdayValidators.validPesonalIdentifier]],
      'birthDate': [this.partner.birthDate ? formatDate(this.partner.birthDate) : '', [Validators.required, WorkdayValidators.validDate]],
      'worksInCompany': [this.partner.worksInCompany]
    });

    if (this.maritalStatus.maritalStatus.label !== 'Căsătorit(ă)') {

      this.maritalStatusFormGroup.get('startingDate').setValue('');
      this.maritalStatusFormGroup.get('startingDate').updateValueAndValidity();
      this.maritalStatusFormGroup.get('startingDate').disable();

      this.maritalStatusFormGroup.get('lastName').setValue('');
      this.maritalStatusFormGroup.get('lastName').updateValueAndValidity();
      this.maritalStatusFormGroup.get('lastName').disable();

      this.maritalStatusFormGroup.get('firstName').setValue('');
      this.maritalStatusFormGroup.get('firstName').updateValueAndValidity();
      this.maritalStatusFormGroup.get('firstName').disable();

      this.maritalStatusFormGroup.get('personIdentifier').setValue('');
      this.maritalStatusFormGroup.get('personIdentifier').updateValueAndValidity();
      this.maritalStatusFormGroup.get('personIdentifier').disable();

      this.maritalStatusFormGroup.get('birthDate').setValue('');
      this.maritalStatusFormGroup.get('birthDate').updateValueAndValidity();
      this.maritalStatusFormGroup.get('birthDate').disable();

      this.maritalStatusFormGroup.get('worksInCompany').setValue(false);
      this.maritalStatusFormGroup.get('worksInCompany').updateValueAndValidity();
      this.maritalStatusFormGroup.get('worksInCompany').disable();
    }

    return this.maritalStatusFormGroup;
  }

  putMaritalStatusAndPartner() {
    if (!this.maritalStatus.maritalStatus) {
      this.maritalStatus.maritalStatus = new Referential();
    }

    this.maritalStatus.maritalStatus.label = this.maritalStatusFormGroup.controls.maritalStatus.value;

    if (this.maritalStatus.maritalStatus.label === 'Căsătorit(ă)') {
      this.maritalStatus.startingDate = parseDate(this.maritalStatusFormGroup.controls.startingDate.value);
        this.partner = new Partner();
        this.partner.lastName = this.maritalStatusFormGroup.controls.lastName.value;
        this.partner.firstName = this.maritalStatusFormGroup.controls.firstName.value;
        this.partner.personIdentifier = this.maritalStatusFormGroup.controls.personIdentifier.value;
        this.partner.birthDate = parseDate(this.maritalStatusFormGroup.controls.birthDate.value);
        this.partner.worksInCompany = this.maritalStatusFormGroup.controls.worksInCompany.value;
        this.partner.employee = this.employee;


      const data = new FormData();
      data.append("marriageCertificate", this.marriageCertificate ? this.marriageCertificate : new Blob(), this.marriageCertificate?.name);
      data.append('maritalStatus', new Blob([JSON.stringify(this.maritalStatus)], {
        type: "application/json"
      }));


      forkJoin([
        this.maritalStatusService.putMaritalStatus(data),
        this.partnerService.putPartner(this.partner)
      ])
        .subscribe(data => {
          this.notificationService.showNotification('top', 'center', 'success', 'Datele au fost modificate cu succes.');
          this.maritalStatusFormGroup.markAsPristine();
          this.maritalStatus = data[0];
          this.partner = data[1];
        })
    } else {

      const data = new FormData();
      data.append("marriageCertificate",  new Blob(), '');
      data.append('maritalStatus', new Blob([JSON.stringify(this.maritalStatus)], {
        type: "application/json"
      }));

      this.partner = new Partner();
      this.partner.employee = this.employee;

      forkJoin([
        this.maritalStatusService.putMaritalStatus(data),
        this.partnerService.putPartner(this.partner)
      ])
        .subscribe(data => {
          this.notificationService.showNotification('top', 'center', 'success', 'Datele au fost modificate cu succes.');
          this.maritalStatusFormGroup.markAsPristine();
          this.maritalStatus = data[0];
          this.partner = data[1];
        })
    }
  }

  onMaritalStatusChange() {
    this.maritalStatusFormGroup.get('maritalStatus').valueChanges.subscribe(selectedValue => {

      if ((selectedValue !== 'Căsătorit(ă)')) {

        this.maritalStatusFormGroup.get('startingDate').setValue('');
        this.maritalStatusFormGroup.get('startingDate').updateValueAndValidity();
        this.maritalStatusFormGroup.get('startingDate').disable();

        this.maritalStatusFormGroup.get('lastName').setValue('');
        this.maritalStatusFormGroup.get('lastName').updateValueAndValidity();
        this.maritalStatusFormGroup.get('lastName').disable();

        this.maritalStatusFormGroup.get('firstName').setValue('');
        this.maritalStatusFormGroup.get('firstName').updateValueAndValidity();
        this.maritalStatusFormGroup.get('firstName').disable();

        this.maritalStatusFormGroup.get('personIdentifier').setValue('');
        this.maritalStatusFormGroup.get('personIdentifier').updateValueAndValidity();
        this.maritalStatusFormGroup.get('personIdentifier').disable();

        this.maritalStatusFormGroup.get('birthDate').setValue('');
        this.maritalStatusFormGroup.get('birthDate').updateValueAndValidity();
        this.maritalStatusFormGroup.get('birthDate').disable();

        this.maritalStatusFormGroup.get('worksInCompany').setValue(false);
        this.maritalStatusFormGroup.get('worksInCompany').updateValueAndValidity();
        this.maritalStatusFormGroup.get('worksInCompany').disable();
      }

      if (selectedValue === "Căsătorit(ă)") {
        this.maritalStatusFormGroup.get('startingDate').setValue('');
        this.maritalStatusFormGroup.get('startingDate').updateValueAndValidity();
        this.maritalStatusFormGroup.get('startingDate').enable();

        this.maritalStatusFormGroup.get('lastName').setValue('');
        this.maritalStatusFormGroup.get('lastName').updateValueAndValidity();
        this.maritalStatusFormGroup.get('lastName').enable();

        this.maritalStatusFormGroup.get('firstName').setValue('');
        this.maritalStatusFormGroup.get('firstName').updateValueAndValidity();
        this.maritalStatusFormGroup.get('firstName').enable();

        this.maritalStatusFormGroup.get('personIdentifier').setValue('');
        this.maritalStatusFormGroup.get('personIdentifier').updateValueAndValidity();
        this.maritalStatusFormGroup.get('personIdentifier').enable();

        this.maritalStatusFormGroup.get('birthDate').setValue('');
        this.maritalStatusFormGroup.get('birthDate').updateValueAndValidity();
        this.maritalStatusFormGroup.get('birthDate').enable();

        this.maritalStatusFormGroup.get('worksInCompany').setValue(false);
        this.maritalStatusFormGroup.get('worksInCompany').updateValueAndValidity();
        this.maritalStatusFormGroup.get('worksInCompany').enable();


        this.maritalStatusFormGroup.get('startingDate').setErrors(null);
        this.maritalStatusFormGroup.get('lastName').setErrors(null);
        this.maritalStatusFormGroup.get('firstName').setErrors(null);
        this.maritalStatusFormGroup.get('personIdentifier').setErrors(null);
        this.maritalStatusFormGroup.get('birthDate').setErrors(null);
        this.maritalStatusFormGroup.get('worksInCompany').setErrors(null);
      }
    });
  }

  createChildForms(): Array<FormGroup> {
    if (this.children) {
      this.childrenFormGroups = new Array<FormGroup>(this.children.length);
      for (let i = 0; i < this.children.length; i++) {
        this.childrenFormGroups[i] = this.formBuilder.group({
          'lastName': [this.children[i].lastName, [Validators.required, Validators.maxLength(30)]],
          'firstName': [this.children[i].firstName, [Validators.required, Validators.maxLength(30)]],
          'personIdentifier': [this.children[i].personIdentifier, [Validators.required, Validators.minLength(13), Validators.maxLength(13), WorkdayValidators.validPesonalIdentifier]],
          'birthDate': [this.children[i].birthDate ? formatDate(this.children[i].birthDate) : '', [Validators.required, WorkdayValidators.validDate]],
          'gender': [this.children[i]?.gender.label, [Validators.required]],
          'worksInCompany': [this.children[i].worksInCompany]
        });
      }
    } else {
      this.isDoesAnyChildExist = false;
    }
    return this.childrenFormGroups;
  }

  createNewChildForm(): FormGroup {

    this.newChild.gender = new Referential();

    this.newChildFormGroup = this.formBuilder.group({
      'lastName': [this.newChild.lastName, [Validators.required, Validators.maxLength(30)]],
      'firstName': [this.newChild.firstName, [Validators.required, Validators.maxLength(30)]],
      'personIdentifier': [this.newChild.personIdentifier, [Validators.required, Validators.minLength(13), Validators.maxLength(13), WorkdayValidators.validPesonalIdentifier]],
      'birthDate': [this.newChild.birthDate ? formatDate(this.newChild.birthDate) : '', [Validators.required, WorkdayValidators.validDate]],
      'gender': [this.newChild?.gender.label, [Validators.required]],
      'worksInCompany': [this.newChild.worksInCompany]
    });
    return this.newChildFormGroup;
  }

  putChild(index: number) {

    this.children[index].lastName = this.childrenFormGroups[index].controls.lastName.value;
    this.children[index].firstName = this.childrenFormGroups[index].controls.firstName.value;
    this.children[index].personIdentifier = this.childrenFormGroups[index].controls.personIdentifier.value;
    this.children[index].birthDate = parseDate(this.childrenFormGroups[index].controls.birthDate.value);
    this.children[index].gender.label = this.childrenFormGroups[index].controls.gender.value;
    this.children[index].worksInCompany = this.childrenFormGroups[index].controls.worksInCompany.value;

    const data = new FormData();
    data.append("birthCertificate", this.birthCertificate ? this.birthCertificate: new Blob(), this.birthCertificate?.name);
    data.append('child', new Blob([JSON.stringify( this.children[index])], {
      type: "application/json"
    }));

    this.childService.putChild(data).subscribe(data => {
      this.childService.getChildren(this.employee.id).subscribe(data => {
        this.childrenFormGroups[index].markAsPristine();
        this.children = data;
        this.isDoesAnyChildExist = true;
        this.notificationService.showNotification('top', 'center', 'success', 'Datele au fost modificate cu succes.');
        setTimeout(() => {

          let children = data as Array<Child>;

          for (let i = 0; i < children.length; i++) {
            $('#' + i).selectpicker();
            $('#' + i).selectpicker('val', children[i].gender.label);
            $('#' + i).selectpicker('refresh');
            if ($('#' + i).val() === '') {
              this.childrenFormGroups[i].markAsPending();
            }
          }
        }, 200);
      });
    });
  }

  putNewChild() {
    if (!this.newChild.gender) {
      this.newChild.gender = new Referential();
    }

    this.newChild.lastName = this.newChildFormGroup.controls.lastName.value;
    this.newChild.firstName = this.newChildFormGroup.controls.firstName.value;
    this.newChild.personIdentifier = this.newChildFormGroup.controls.personIdentifier.value;
    this.newChild.birthDate = parseDate(this.newChildFormGroup.controls.birthDate.value);
    this.newChild.gender.label = this.newChildFormGroup.controls.gender.value;
    this.newChild.worksInCompany = this.newChildFormGroup.controls.worksInCompany.value;
    this.newChild.employee = this.employee;

    const data = new FormData();
    data.append("birthCertificate", this.birthCertificate ? this.birthCertificate: new Blob(), this.birthCertificate?.name);
    data.append('child', new Blob([JSON.stringify(this.newChild)], {
      type: "application/json"
    }));

    this.childService.putChild(data).subscribe(data => {
      this.children.push(data as Child);
      this.createChildForms();
      this.isDoesAnyChildExist = true;
      this.notificationService.showNotification('top', 'center', 'success', 'Datele au fost salvate cu succes.');
      setTimeout(() => {

        for (let i = 0; i < this.children.length; i++) {
          $('#' + i).selectpicker();
          $('#' + i).selectpicker('val', this.children[i].gender.label);
          $('#' + i).selectpicker('refresh');
          if ($('#' + i).val() === '') {
            this.childrenFormGroups[i].markAsPending();
          }
        }
      }, 200);
    });
  }

  uploadMarriageCertificateFile(event) {
    this.marriageCertificate = event.target.files[0];
  }

  uploadBirthCertificateFile(event, index) {
    this.birthCertificates[index] = event.target.files[0];
  }

  uploadNewBirthCertificateFile(event) {
    this.birthCertificate = event.target.files[0];
  }

  show(i: number) {
    console.log(this.childrenFormGroups[i]);
  }
}
