import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MaritalStatus} from "../../../shared/models/marital-status.model";

const MARITAL_STATUS_API = 'maritalStatus/';

const MARITAL_STATUS_API_MULTIPART = 'maritalStatus/multipart/marriageCertificate/';


@Injectable({
  providedIn: 'root'
})
export class MaritalStatusService {

  constructor(private http: HttpClient) {
  }

  getMaritalStatus(employeeId: number): Observable<MaritalStatus> {
    return this.http.get<MaritalStatus>(MARITAL_STATUS_API + employeeId);
  }

  putMaritalStatus(formData: FormData): Observable<MaritalStatus> {
    return this.http.put<MaritalStatus>(MARITAL_STATUS_API_MULTIPART, formData);
  }

}
