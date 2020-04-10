import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const NATIONALITY_REFERENTIAL_API = 'nationalityReferential/';


@Injectable({
  providedIn: 'root'
})
export class NationalityReferentialService {

  constructor(private http: HttpClient) {
  }

  getNationalityReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(NATIONALITY_REFERENTIAL_API);
  }

}
