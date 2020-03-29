import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const JOB_DOMAIN_REFERENTIAL_API = 'jobDomainReferential/';


@Injectable({
  providedIn: 'root'
})
export class JobDomainReferentialService {

  constructor(private http: HttpClient) {
  }

  getJobDomainReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(JOB_DOMAIN_REFERENTIAL_API);
  }

}
