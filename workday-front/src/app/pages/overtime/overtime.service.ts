import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Overtime} from "../../shared/models/overtime.model";

const OVERTIME_API = 'overtime/';


@Injectable({
  providedIn: 'root'
})
export class OvertimeService {

  constructor(private http: HttpClient) {
  }

  getOvertimeList(employeeId: number): Observable<Array<Overtime>> {
    return this.http.get<Array<Overtime>>(OVERTIME_API + employeeId);
  }

  putOvertime(overtime: Overtime): Observable<Overtime> {
    return this.http.put<Overtime>(OVERTIME_API, overtime);
  }

}
