import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MedicalService} from "../../shared/models/medical-service.model";
import {WorkFromHome} from "../../shared/models/work-from-home.model";

const WORK_FROM_HOME_API = 'workFromHome/';


@Injectable({
  providedIn: 'root'
})
export class WorkFromHomeService {

  constructor(private http: HttpClient) {
  }

  getWorkFromHome(employeeId: number): Observable<WorkFromHome> {
    return this.http.get<WorkFromHome>(WORK_FROM_HOME_API + employeeId);
  }

  putWorkFromHome(workFromHome: WorkFromHome): Observable<WorkFromHome> {
    return this.http.put<WorkFromHome>(WORK_FROM_HOME_API, workFromHome);
  }

}
