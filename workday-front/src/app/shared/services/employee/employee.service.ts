import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {Employee} from "../../models/employee.model";

const EMPLOYEE_API = 'employee/';

const EMPLOYEES_API = 'employee/manager/employees/';

const MANAGER_API = 'employee/manager/';

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

  constructor(private http: HttpClient) {
  }


  getEmployee(username: string): Observable<Employee> {
    return this.http.get<Employee>(EMPLOYEE_API + username);
  }

  putEmployee(employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(EMPLOYEE_API, employee);
  }

  getEmployeesByManagerId(managerId: number): Observable<Array<Employee>> {
    return this.http.get<Array<Employee>>(EMPLOYEES_API + managerId);
  }

  getManager(managerId: number): Observable<Employee> {
    return this.http.get<Employee>(MANAGER_API + managerId);
  }


}
