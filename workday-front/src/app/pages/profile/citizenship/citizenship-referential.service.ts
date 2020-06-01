import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const CITIZENSHIP_REFERENTIAL_API = 'citizenshipReferential/';


@Injectable({
  providedIn: 'root'
})
export class CitizenshipReferentialService {

  constructor(private http: HttpClient) {
  }

  getCitizenshipReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(CITIZENSHIP_REFERENTIAL_API);
  }

}
