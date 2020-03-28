import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const CURRENCY_REFERENTIAL_REFERENTIAL_API = 'currencyReferential/';


@Injectable({
  providedIn: 'root'
})
export class CurrencyReferentialService {

  constructor(private http: HttpClient) {
  }

  getCurrencyReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(CURRENCY_REFERENTIAL_REFERENTIAL_API);
  }

}
