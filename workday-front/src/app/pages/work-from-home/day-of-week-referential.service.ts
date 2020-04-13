import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../shared/models/referential.model";

const DAY_OF_WEEK_REFERENTIAL_API = 'dayOfWeekReferential/';


@Injectable({
  providedIn: 'root'
})
export class DayOfWeekReferentialService {

  constructor(private http: HttpClient) {
  }

  getDayOfWeekReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(DAY_OF_WEEK_REFERENTIAL_API);
  }

}
