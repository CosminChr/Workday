import {AfterViewInit, Component, OnInit} from '@angular/core';
import {MedicalService} from "../../shared/models/medical-service.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MedicalServiceService} from "./medical-service.service";
import {forkJoin} from "rxjs";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {Employee} from "../../shared/models/employee.model";
import {Referential} from "../../shared/models/referential.model";
import {MedicalServiceProviderReferentialService} from "./medical-service-provider-referential.service";
import {formatDate, parseDate} from "../../shared/utils/utils";
import {NotificationService} from "../../shared/services/notification/notification.service";

declare var $: any;

@Component({
  selector: 'workday-medical-services',
  templateUrl: './medical-services.component.html',
  styleUrls: ['./medical-services.component.scss']
})
export class MedicalServicesComponent implements OnInit, AfterViewInit {

  employee: Employee;

  medicalService: MedicalService;

  medicalServiceFormGroup: FormGroup;

  medicalServiceProviderReferentials: Array<Referential>;

  validityDate: string;

  isHasUserAcceptedTerms = false;


  constructor(private medicalServiceService: MedicalServiceService,
              private medicalServiceProviderReferentialService: MedicalServiceProviderReferentialService,
              private employeeService: EmployeeService,
              private notificationService: NotificationService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();

    forkJoin([
      this.medicalServiceService.getMedicalService(this.employee.id),
      this.medicalServiceProviderReferentialService.getMedicalServiceProviderReferentials()
    ])
      .subscribe(data => {
        this.medicalService = data[0] as MedicalService;
        this.medicalServiceProviderReferentials = data[1] as Array<Referential>;
        this.createMedicalServiceForm();
      })
  }

  ngAfterViewInit(): void {

    this.medicalServiceService.getMedicalService(this.employee.id)
      .subscribe((data: MedicalService) => {
        setTimeout( () => {
          $('.selectpicker').selectpicker();
          if (data?.medicalServiceProvider.label) {
            $("#medicalServiceProvider").prop("disabled", true);
            $('#medicalServiceProvider').selectpicker('val', data?.medicalServiceProvider.label);
          }
          $('#medicalServiceProvider').selectpicker('refresh');
        }, 200);
      });
  }

  createMedicalServiceForm(): FormGroup {
    if (!this.medicalService) {
      this.medicalService = new MedicalService();
      this.medicalService.medicalServiceProvider = new Referential();
    }

    let initiationDateAsString: string;
    if (!this.medicalService.initiationDate) {
      this.medicalService.initiationDate = new Date();
      initiationDateAsString = this.medicalService.initiationDate.getDate() + '-' +
        ((this.medicalService.initiationDate.getMonth() + 1) < 10 ? '0' + (this.medicalService.initiationDate.getMonth() + 1) :
          (this.medicalService.initiationDate.getMonth() + 1)) + '-' + this.medicalService.initiationDate.getFullYear();
    } else {
      initiationDateAsString = formatDate(this.medicalService.initiationDate);
    }


    let validityDate = new Date(new Date(this.medicalService.initiationDate).getFullYear()+3,
      new Date(this.medicalService.initiationDate).getMonth()+1,
      1) ;


    this.validityDate = new Date(validityDate).getDate() + '-' +
      ((new Date(validityDate).getMonth() + 1) < 10 ? '0' + (new Date(validityDate).getMonth() + 1) :
        (new Date(this.medicalService.initiationDate).getMonth() + 1)) + '-' + new Date(validityDate).getFullYear();


    this.medicalServiceFormGroup = this.formBuilder.group({
      'medicalServiceProvider': [this.medicalService?.medicalServiceProvider.label, [Validators.required, Validators.maxLength(30)]],
      'initiationDate': [initiationDateAsString],
      'userAcceptance': [this.isHasUserAcceptedTerms]
    });

    return this.medicalServiceFormGroup;
  }

  onHasUserAcceptedTermsChange (event) {
    this.isHasUserAcceptedTerms = event.target.checked;
  }

  putMedicalService() {
   this.medicalService.medicalServiceProvider.label = this.medicalServiceFormGroup.controls.medicalServiceProvider.value;
   this.medicalService.initiationDate = parseDate(this.medicalServiceFormGroup.controls.initiationDate.value);
   this.medicalService.employee = this.employee;

    this.medicalServiceService.putMedicalService(this.medicalService).subscribe( data => {
      this.notificationService.showNotification('top','center', 'success', 'Datele au fost modificate cu succes.');
      this.medicalService = data as MedicalService;
      console.log(this.medicalService);
      this.createMedicalServiceForm();
      setTimeout( () => {
        $('.selectpicker').selectpicker();
        if (data?.medicalServiceProvider.label) {
          $("#medicalServiceProvider").prop("disabled", true);
          $('#medicalServiceProvider').selectpicker('val', data?.medicalServiceProvider.label);
        }
        $('#medicalServiceProvider').selectpicker('refresh');
      }, 200);
    });
  }

}
