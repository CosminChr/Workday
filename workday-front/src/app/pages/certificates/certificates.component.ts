import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Certificate} from "../../shared/models/certificate.model";
import {Referential} from "../../shared/models/referential.model";
import {CertificateTypeReferentialService} from "./certificate-type-referential.service";
import {forkJoin} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {Employee} from "../../shared/models/employee.model";

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

  constructor(private certificateTypeReferentialService: CertificateTypeReferentialService,
              private employeeService: EmployeeService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();

    forkJoin([
      this.certificateTypeReferentialService.getCertificateTypeReferentials()
    ])
      .subscribe( data => {
        this.certificateTypeReferentials = data[0] as Array<Referential>;
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

}
