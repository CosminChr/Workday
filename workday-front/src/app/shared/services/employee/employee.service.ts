import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";

const API_URL = 'http://localhost:8080/api/test/';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  employee: any;

  storedEmployee = new BehaviorSubject(this.employee);

  getSavedEmployee() {
    return this.employee;
  }

  getStoredEmployee() {
    return this.storedEmployee;
  }

  setStoredEmployee(employee) {
    this.employee = employee;
    this.storedEmployee.next(employee);
  }





  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

  getCurrentUser() {
    return
  }


}
