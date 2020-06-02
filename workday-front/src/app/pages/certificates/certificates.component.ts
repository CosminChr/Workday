import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Certificate} from "../../shared/models/certificate.model";
import {Referential} from "../../shared/models/referential.model";
import {CertificateTypeReferentialService} from "./certificate-type-referential.service";
import {forkJoin} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {Employee} from "../../shared/models/employee.model";
import * as jsPDF from 'jspdf';
import * as NotoSans from '../../../assets/js/NotoSans-Regular-normal.js';
import {AddressService} from "../profile/address/address.service";
import {Address} from "../../shared/models/address.model";
import {IdentityDocument} from "../../shared/models/identity-document.model";
import {IdentityDocumentService} from "../profile/personal-documents/identity-document.service";

declare var $: any;

@Component({
  selector: 'workday-certificates',
  templateUrl: './certificates.component.html',
  styleUrls: ['./certificates.component.scss']
})
export class CertificatesComponent implements OnInit, AfterViewInit {

  employee: Employee;

  newCertificate = new Certificate();

  certificateTypeReferentials: Array<Referential>;

  newCertificateFormGroup: FormGroup;

  addresses: Array<Address>;

  identityDocuments: Array<IdentityDocument>;

  constructor(private certificateTypeReferentialService: CertificateTypeReferentialService,
              private employeeService: EmployeeService,
              private addressService: AddressService,
              private identityDocumentService: IdentityDocumentService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();
    forkJoin([
      this.certificateTypeReferentialService.getCertificateTypeReferentials(),
      this.addressService.getAddresses(this.employee.id),
      this.identityDocumentService.getIdentityDocuments(this.employee.id)
    ])
      .subscribe( data => {
        this.certificateTypeReferentials = data[0] as Array<Referential>;
        this.addresses = data[1] as Array<Address>;
        this.identityDocuments = data[2] as Array<IdentityDocument>;
        this.createCertificateForm();
      })
  }

  ngAfterViewInit(): void {

    this.certificateTypeReferentialService.getCertificateTypeReferentials()
      .subscribe( (data: Array<Referential>) => {
        setTimeout(function () {
          $('.selectpicker').selectpicker();
          for (let i = 0; i < data.length; i++) {
            $('#certificateType').selectpicker('refresh');
          }
        }, 200);
      });

  }

  createCertificateForm(): FormGroup {

    this.newCertificate.certificateType = new Referential();
    this.newCertificate.generationDate = new Date();

    const date = this.newCertificate.generationDate.getDate() + '-' +
      ((this.newCertificate.generationDate.getMonth() + 1) < 10 ? '0' + (this.newCertificate.generationDate.getMonth() + 1):
        (this.newCertificate.generationDate.getMonth() + 1)) + '-' + this.newCertificate.generationDate.getFullYear();


    this.newCertificateFormGroup = this.formBuilder.group({
      'certificateType': [this.newCertificate.certificateType.label, [Validators.required, Validators.maxLength(30)]],
      'generationDate': [date]
    });

    return this.newCertificateFormGroup;
  }

  functie() {

    let doc = new jsPDF();
    const img = new Image();
    img.src = './assets/img/societe-generale.jpg';
    doc.addImage(img, 'JPEG', 20, 10, 70, 30);
    doc.setTextColor(192,192,192);
    doc.setFontSize(11);
    doc.text('Marca: 82780',170,60);
    doc.setTextColor(0,0,0);
    doc.setFontSize(12);
    doc.setFontType('bold');
    doc.text('ADEVERINTA', 95,80 );
    doc.text('NR ', 86, 90);
    const certificateNumber = Math.floor(Math.random() * 10000) + 1;
    doc.setFontType('normal');
    doc.text(certificateNumber.toString() + ' / '+ new Date(Date.now()).toLocaleString('en-GB').split(',')[0], 94, 90);
    const title = this.employee.gender?.id === 1 ? 'domnul' : 'doamna';
    NotoSans;
    doc.setFont('NotoSans-Regular');
    doc.setFontSize(10);
    const resident = this.employee.gender?.id === 1 ? 'domiciliat' : 'domiciliată';
    const permanentAddress : Address = this.addresses.filter(address => address.addressType.id === 1)[0];
    doc.text('Prin prezenta adeverim ca ' + title.toString() + ' ' + this.employee.lastName + ' ' + this.employee.firstName + ' '
      + resident + ' în localitatea ' + 'str. ' + permanentAddress.street + ', nr. ', 25, 116);
    const owner = this.employee.gender?.id === 1 ? 'posesor' : 'posesoare';
    const identityCard: IdentityDocument = this.identityDocuments.filter(document => document.identityDocumentType.id === 1)[0];
    doc.text(permanentAddress.number + ' bl. ' + permanentAddress.block + ', scara ' +
      + permanentAddress.stairwell + ', etajul ' + permanentAddress.floor + ', judet ' + permanentAddress.locality.county.label + ', '
      + owner + ' a cărții de identitate seria ' + identityCard.seriesAndNumber.substring(0,2) + ' nr. '
      + identityCard.seriesAndNumber.substring(2,identityCard.seriesAndNumber.length ) + ', eliberată', 25, 122);
    const employed = this.employee.gender?.id === 1 ? 'angajat' : 'angajată';
    const entity = this.employee.entity.split(' ');
    doc.text('de ' + identityCard.issuer + ', la data de ' + new Date(identityCard.issueDate).toLocaleString('en-GB').split(',')[0] +
      'CNP ' + this.employee.personIdentifier + ', este ' + employed + ' ' + (entity[0] ? entity[0]: '') + ' ' + (entity[1] ? entity[1]: '') , 25, 128);
    doc.text((entity[2] ? entity[2]: '') + ' ' + (entity[3] ? entity[3]: '') + ' ' + (entity[4] ? entity[4]: '') + ' ' + (entity[5] ? entity[5]: '')
      + ' din data de ' + new Date(this.employee.joiningDate).toLocaleString('en-GB').split(',')[0] + ' cu contract de muncă pe durată ', 25, 134);
    const codNumber = Math.floor((Math.random() * 100000) + 300000);
    doc.text('nedeterminată, ocupând în prezent funcția de ' + this.employee.jobPosition.label + ' Cod COR ' + codNumber, 25, 140);
    doc.text('S-a eliberat prezenta la cerere, pentru a-i servi la ' + this.newCertificateFormGroup.controls.certificateType.value.toString().toLowerCase() + '.', 25, 152);
    doc.setFontSize(11);
    doc.setFontType('bold');
    doc.text("Director Resurese Umane", 95, 167);
    doc.text("Daniela VERCELLINO", 96, 173);
    const img2 = new Image();
    img2.src = './assets/img/hr-signature.jpg';
    doc.addImage(img2, 'JPEG', 94, 180, 60, 50);
    doc.line(25, 245, 190, 245);
    doc.setFontSize(9);
    doc.setFontType('normal');
    doc.text("Societe Generale European Business Services S.A.", 25, 250);
    doc.text("West Gate Park - Strada Preciziei, nr 24, cladire H4, etaj 5, 062204, Bucuresti, România", 25, 254);
    doc.text("Capital social: 12.900.000 RON; R.C R.C J40/151/2011; C.U.I./C.I.F.: 27883477", 25, 258);
    doc.text("Numar de Inregistrare in Registrul Operatorilor de Date cu Caracter Personal 25980", 25, 262);
    doc.text("Telefon: +4037.416.75.24; Fax: +4021.310.82.00", 25, 266);
    doc.save("Adeverință " + this.employee.lastName + ' ' + this.employee.firstName + '.pdf');
  }
}

