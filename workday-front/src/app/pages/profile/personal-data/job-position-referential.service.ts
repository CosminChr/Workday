import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const JOB_POSITION_REFERENTIAL_API = 'jobPositionReferential/';


@Injectable({
  providedIn: 'root'
})
export class JobPositionReferentialService {

  constructor(private http: HttpClient) {
  }

  getJobPositionReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(JOB_POSITION_REFERENTIAL_API);
  }

}
