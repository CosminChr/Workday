import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const COUNTRY_REFERENTIAL_API = 'countryReferential/';


@Injectable({
  providedIn: 'root'
})
export class CountryReferentialService {

  constructor(private http: HttpClient) {
  }

  getCountryReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(COUNTRY_REFERENTIAL_API);
  }

}
