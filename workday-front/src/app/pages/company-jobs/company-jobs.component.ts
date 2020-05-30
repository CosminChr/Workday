import {Component, OnInit} from '@angular/core';
import {Employee} from "../../shared/models/employee.model";
import {CompanyJob} from "../../shared/models/company-job.model";
import {JobApplication} from "../../shared/models/job-application.model";
import {CompanyJobService} from "./company-job.service";
import {forkJoin} from "rxjs";
import {JobApplicationService} from "./job-application.service";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {NotificationService} from "../../shared/services/notification/notification.service";

declare var $: any;

@Component({
  selector: 'workday-company-jobs',
  templateUrl: './company-jobs.component.html',
  styleUrls: ['./company-jobs.component.scss']
})
export class CompanyJobsComponent implements OnInit {

  employee: Employee;

  companyJobs: Array<CompanyJob>;

  jobApplications: Array<JobApplication>;

  newJobApplication = new JobApplication();

  selectedCompanyJob = new Array<string>();

  cv: any;

  newCVRowIsSelected = false;

  constructor(private companyJobService: CompanyJobService,
              private jobApplicationService: JobApplicationService,
              private employeeService: EmployeeService,
              private notificationService: NotificationService) {
  }

  ngOnInit() {

    this.employee = this.employeeService.getSavedEmployee();
    forkJoin([
      this.companyJobService.getCompanyJobs(),
      this.jobApplicationService.getJobApplications(this.employee.id)
    ]).subscribe(data => {
      this.companyJobs = data[0];
      this.jobApplications = data[1];
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
          searchPlaceholder: "Caută job",
          paginate: {
            first: "Prima pagină",
            previous: "Înapoi",
            next: "Înainte",
            last: "Ultima pagină"
          }
        }

      });
    }, 1000);
  }

  isTheCVUploaded(companyJob: CompanyJob, index): boolean {

    return  (this.jobApplications && this.jobApplications.filter(jobApplication => jobApplication.companyJob.id === companyJob.id).length > 0) || $('#uploadBtn-' + index).attr('class') === 'btn btn-file btn-sm btn-success' ? true : false;
  }

  uploadFile(event, companyJob: CompanyJob, index) {
    if (!this.cv) {
      companyJob.selected = true;
      this.newCVRowIsSelected = true;
      this.selectedCompanyJob[0] = this.selectedCompanyJob[0] = event.path[3].cells[0].childNodes[0].data;
      this.cv = event.target.files[0];
      if (event.target.files && event.target.files[0]) {
        $('#uploadBtn-' + index).removeClass("btn-danger");
        $('#uploadBtn-' + index).addClass("btn-success");
      } else {
        $('#uploadBtn-' + index).removeClass("btn-success");
        $('#uploadBtn-' + index).addClass("btn-danger");
      }
    } else {
      if (this.cv.name !== event.target.files[0].name && $('#uploadBtn-' + index).attr('class') === 'btn btn-file btn-sm btn-success') {
        this.cv = event.target.files[0];
      } else {
        this.notificationService.showNotification('top','center', 'warning', 'Ai încărcat deja un cv. Nu poți aplica la mai mult de un job o dată.');
      }
    }
  }


  openFile(companyJob: CompanyJob) {


     if (this.cv) {
       let fileURL = URL.createObjectURL(this.cv);

       window.open(fileURL, '_blank');
     } else {

       const cv = this.jobApplications && this.jobApplications.filter(jobApplication => jobApplication.companyJob.id === companyJob.id)[0].cv;
       const byteCharacters = atob(cv);
       const byteNumbers = new Array(byteCharacters.length);
       for (let i = 0; i < byteCharacters.length; i++) {
         byteNumbers[i] = byteCharacters.charCodeAt(i);
       }
       const byteArray = new Uint8Array(byteNumbers);
       const file = new Blob([byteArray], {type: 'application/pdf;base64'});
       const fileURL = URL.createObjectURL(file);
       window.open(fileURL);
     }
  }

  putNewJobApplication() {
    if (this.cv) {
      this.newJobApplication.companyJob = new CompanyJob();
      this.newJobApplication.companyJob.id = Number(this.selectedCompanyJob[0]);
      this.newJobApplication.submittedBy = this.employee;
      const data = new FormData();
      data.append("cv", this.cv, this.cv.name);
      data.append('jobApplication', new Blob([JSON.stringify(this.newJobApplication)], {
        type: "application/json"
      }));
      this.jobApplicationService.putJobApplication(data).subscribe(data => {
        this.cv = null;
        this.newCVRowIsSelected = false;
        $('.companyJob').removeClass("selected");
      });
    } else {
      this.notificationService.showNotification('top','center', 'warning', 'Acest CV a fost încărcat deja.');
    }
  }
}
