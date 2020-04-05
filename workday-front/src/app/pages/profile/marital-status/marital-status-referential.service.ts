import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const MARITAL_STATUS_REFERENTIAL_API = 'maritalStatusReferential/';


@Injectable({
  providedIn: 'root'
})
export class MaritalStatusReferentialService {

  constructor(private http: HttpClient) {
  }

  getMaritalStatusReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(MARITAL_STATUS_REFERENTIAL_API);
  }

}
