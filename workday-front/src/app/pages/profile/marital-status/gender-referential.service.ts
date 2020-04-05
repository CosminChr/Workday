import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const GENDER_REFERENTIAL_API = 'genderReferential/';


@Injectable({
  providedIn: 'root'
})
export class GenderReferentialService {

  constructor(private http: HttpClient) {
  }

  getGenderReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(GENDER_REFERENTIAL_API);
  }

}
