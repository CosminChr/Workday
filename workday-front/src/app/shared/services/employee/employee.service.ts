import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {Employee} from "../../models/employee.model";
import {AuthService} from "../../../core/services/security/auth.service";

const EMPLOYEE_API = 'employee/';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  employee: Employee;

  storedEmployee = new BehaviorSubject(this.employee);

  getSavedEmployee(): Employee {
    return this.employee;
  }

  getStoredEmployee() {
    return this.storedEmployee;
  }

  setStoredEmployee(employee) {
    this.employee = employee;
    this.storedEmployee.next(employee);
  }

  constructor(private http: HttpClient,
              private authService: AuthService) {
  }


  getEmployee(username: string): Observable<Employee> {
    return this.http.get<Employee>(EMPLOYEE_API + username);
  }

  putEmployee(employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(EMPLOYEE_API, employee);
  }


}
