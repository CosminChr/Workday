import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {CompanyJob} from "../../shared/models/company-job.model";

const COMPANY_JOB_API = 'companyJob/';


@Injectable({
  providedIn: 'root'
})
export class CompanyJobService {

  constructor(private http: HttpClient) {
  }

  getCompanyJobs(): Observable<Array<CompanyJob>> {
    return this.http.get<Array<CompanyJob>>(COMPANY_JOB_API);
  }

  putCompanyJob(companyJob: CompanyJob): Observable<CompanyJob> {
    return this.http.put<CompanyJob>(COMPANY_JOB_API, companyJob);
  }

}
