import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {IdentityDocument} from "../../../shared/models/identity-document.model";
import {PreviousJob} from "../../../shared/models/previous-job.model";

const PREVIOUS_JOB_API = 'previousJob/';


@Injectable({
  providedIn: 'root'
})
export class PreviousJobService {

  constructor(private http: HttpClient) {
  }

  getPreviousJobs(employeeId: number): Observable<Array<PreviousJob>> {
    return this.http.get<Array<PreviousJob>>(PREVIOUS_JOB_API + employeeId);
  }

  putPreviousJob(previousJob: PreviousJob): Observable<PreviousJob> {
    return this.http.put<PreviousJob>(PREVIOUS_JOB_API, previousJob);
  }
}
