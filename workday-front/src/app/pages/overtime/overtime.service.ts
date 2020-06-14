import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Overtime} from "../../shared/models/overtime.model";
import {WorkFromHome} from "../../shared/models/work-from-home.model";

const OVERTIME_API = 'overtime/';

const OVERTIME_EMPLOYEES_API = 'overtime/employees/';


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

  getOvertimeForEmployeesOfManager(managerId: number): Observable<Array<Overtime>> {
    return this.http.get<Array<Overtime>>(OVERTIME_EMPLOYEES_API + managerId);
  }

}
