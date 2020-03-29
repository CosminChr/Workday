import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Employee} from "../../../shared/models/employee.model";
import {Referential} from "../../../shared/models/referential.model";
import {LocalityReferential} from "../../../shared/models/locality.model";
import {PreviousJob} from "../../../shared/models/previous-job.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EmployeeService} from "../../../shared/services/employee/employee.service";
import {JobDomainReferentialService} from "./job-domain-referential.service";
import {PreviousJobService} from "./prebious-job.service";
import {forkJoin} from "rxjs";
import {LocalityReferentialService} from "../address/locality-referential.service";
import {formatDate, parseDate} from "../../../shared/utils/utils";

declare var $: any;

@Component({
  selector: 'workday-employment-history',
  templateUrl: './employment-history.component.html',
  styleUrls: ['./employment-history.component.scss']
})
export class EmploymentHistoryComponent implements OnInit, AfterViewInit {

  employee: Employee;

  jobDomainReferentials: Array<Referential>;

  localityReferentials: Array<LocalityReferential>;

  previousJobs: Array<PreviousJob>;

  newPreviousJob = new PreviousJob();

  previousJobFormGroups: Array<FormGroup>;

  previousJobFormGroup: FormGroup;

  isDoesAnyPreviousJobExist = true;

  selectedLocality = new Array<string>();

  constructor(private employeeService: EmployeeService,
              private jobDomainReferentialService: JobDomainReferentialService,
              private localityReferentialService: LocalityReferentialService,
              private previousJobService: PreviousJobService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();

    forkJoin([
      this.jobDomainReferentialService.getJobDomainReferentials(),
      this.previousJobService.getPreviousJobs(this.employee.id)
    ])
      .subscribe(data => {
        this.jobDomainReferentials = data[0] as Array<Referential>;
        this.previousJobs = data[1] as Array<PreviousJob>;
        this.createPreviousJobForms();
        this.createNewPreviousJobForm();
      });

    this.localityReferentialService.getLocalityReferentials().subscribe(data => {
      this.localityReferentials = data as Array<LocalityReferential>;
    });

  }

  ngAfterViewInit(): void {


    $('.selectpicker').selectpicker();

    setTimeout(function () {
      $('#datatable').DataTable({
        lengthChange: false,
        bInfo: false,
        pagingType: "full_numbers",
        lengthMenu: [
          [7],
          [7]
        ],
        responsive: true,
        language: {
          search: "_INPUT_",
          searchPlaceholder: "Caută oraș",
          paginate: {
            first: "Prima pagină",
            previous: "Înapoi",
            next: "Înainte",
            last: "Ultima pagină"
          }
        }

      });
    }, 500);
  }

  reinitializePicker() {
    $('.selectpicker').selectpicker('refresh');
  }

  createPreviousJobForms(): Array<FormGroup> {
    if (this.previousJobs) {
      this.previousJobFormGroups = new Array<FormGroup>(this.previousJobs.length);
      for (let i = 0; i < this.previousJobs.length; i++) {
        this.previousJobFormGroups[i] = this.formBuilder.group({
          'employer': [this.previousJobs[i].employer, [Validators.required, Validators.maxLength(100)]],
          'jobDomain': [this.previousJobs[i]?.jobDomain.label, [Validators.required, Validators.maxLength(100)]],
          'position': [this.previousJobs[i].position, [Validators.required, Validators.maxLength(100)]],
          'locality': [this.previousJobs[i]?.locality.label, [Validators.required, Validators.maxLength(100)]],
          'fromDate': [this.previousJobs[i].fromDate ? formatDate(this.previousJobs[i].fromDate) : '', [Validators.required, Validators.maxLength(100)]],
          'toDate': [this.previousJobs[i].toDate ? formatDate(this.previousJobs[i].toDate) : '', [Validators.required, Validators.maxLength(100)]],
        });
      }
    } else {
      this.isDoesAnyPreviousJobExist = false;
    }
    return this.previousJobFormGroups;
  }

  createNewPreviousJobForm(): FormGroup {

    this.newPreviousJob.locality = new LocalityReferential();
    this.newPreviousJob.locality.county = new Referential();
    this.newPreviousJob.locality.country = new Referential();
    this.newPreviousJob.jobDomain = new LocalityReferential();

    this.previousJobFormGroup = this.formBuilder.group({
      'employer': [this.newPreviousJob.employer, [Validators.required, Validators.maxLength(100)]],
      'jobDomain': [this.newPreviousJob?.jobDomain.label, [Validators.required, Validators.maxLength(100)]],
      'position': [this.newPreviousJob.position, [Validators.required, Validators.maxLength(100)]],
      'locality': [this.newPreviousJob?.locality.label, [Validators.required, Validators.maxLength(100)]],
      'fromDate': [this.newPreviousJob.fromDate ? formatDate(this.newPreviousJob.fromDate) : '', [Validators.required, Validators.maxLength(100)]],
      'toDate': [this.newPreviousJob.toDate ? formatDate(this.newPreviousJob.toDate) : '', [Validators.required, Validators.maxLength(100)]],
    });
    return this.previousJobFormGroup;
  }

  isALocalitySelected(): boolean {
    return this.selectedLocality[0] != null;
  }

  setLocality(): string {
    if (this.isALocalitySelected()) {
      return this.selectedLocality[0];
    } else {
      return "Oraș";
    }
  }

  selectRow(event: any) {
    this.selectedLocality[0] = event.target.parentNode.cells[0].textContent;
    this.selectedLocality[1] = event.target.parentNode.cells[1].textContent;
    this.selectedLocality[2] = event.target.parentNode.cells[2].textContent;
  }

  putNewPreviousJob() {

    if (!this.newPreviousJob.locality) {
      this.newPreviousJob.locality = new LocalityReferential();
      this.newPreviousJob.locality.county = new Referential();
      this.newPreviousJob.locality.country = new Referential();
    }

    if (!this.newPreviousJob.jobDomain) {
      this.newPreviousJob.jobDomain = new Referential();
    }

    this.newPreviousJob.employer = this.previousJobFormGroup.controls.employer.value;
    this.newPreviousJob.jobDomain.label = this.previousJobFormGroup.controls.jobDomain.value;
    this.newPreviousJob.position = this.previousJobFormGroup.controls.position.value;
    this.newPreviousJob.locality.label = this.selectedLocality[0];
    this.newPreviousJob.locality.county.label = this.selectedLocality[1];
    this.newPreviousJob.locality.country.label = this.selectedLocality[2];


    this.newPreviousJob.fromDate = parseDate(this.previousJobFormGroup.controls.fromDate.value);
    this.newPreviousJob.toDate = parseDate(this.previousJobFormGroup.controls.toDate.value);

    this.newPreviousJob.employee = this.employee;
    this.previousJobService.putPreviousJob(this.newPreviousJob).subscribe(data => {
      this.previousJobs.push(data as PreviousJob);
    });
  }


}
