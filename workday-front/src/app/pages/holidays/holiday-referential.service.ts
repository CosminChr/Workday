import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../shared/models/referential.model";

const HOLIDAYS_API = 'holidayReferential/';


@Injectable({
  providedIn: 'root'
})
export class HolidaysReferentialService {

  constructor(private http: HttpClient) {
  }

  getHolidayReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(HOLIDAYS_API);
  }

}
