import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {Holiday} from "../../shared/models/holiday.model";
import {HttpClient} from "@angular/common/http";

const HOLIDAYS_API = 'holiday/';


@Injectable({
  providedIn: 'root'
})
export class HolidaysService {

  constructor(private http: HttpClient) {
  }

  getHolidays(employeeId: number): Observable<Array<Holiday>> {
    return this.http.get<Array<Holiday>>(HOLIDAYS_API + employeeId);
  }

  putHoliday(holiday: Holiday): Observable<Holiday> {
    return this.http.put<Holiday>(HOLIDAYS_API, holiday);
  }

}
