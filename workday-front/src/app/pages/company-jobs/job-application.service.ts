import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {JobApplication} from "../../shared/models/job-application.model";

const JOB_APPLICATION_FILE_API = 'jobApplication/multipart/cv/';
const JOB_APPLICATION_API = 'jobApplication/';


@Injectable({
  providedIn: 'root'
})
export class JobApplicationService {

  constructor(private http: HttpClient) {
  }

  getJobApplications(employeeId: number): Observable<Array<JobApplication>> {
    return this.http.get<Array<JobApplication>>(JOB_APPLICATION_API + employeeId);
  }

  putJobApplication(formData: FormData): Observable<JobApplication> {
    return this.http.put<JobApplication>(JOB_APPLICATION_FILE_API, formData);
  }

}
