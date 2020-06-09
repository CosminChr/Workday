import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {Holiday} from "../../shared/models/holiday.model";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Employee} from "../../shared/models/employee.model";

const HOLIDAYS_API = 'holiday/';

const HOLIDAYS_EMPLOYEES_API = 'holiday/employees/';


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

  getHolidaysForEmployees(employees: Array<Employee>): Observable<Array<Holiday>> {
    return this.http.get<Array<Holiday>>(HOLIDAYS_EMPLOYEES_API,  {
      params: employees.reduce((accumulator, employee) => accumulator.append('employeeIds', employee.id.toString()), new HttpParams())
    });
  }

}
