import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Citizenship} from "../../../shared/models/citizenship.model";

const CITIZENSHIP_API = 'citizenship/';

const CITIZENSHIP_API_MULTIPART = 'citizenship/multipart/citizenshipCertificate/';


@Injectable({
  providedIn: 'root'
})
export class CitizenshipService {

  constructor(private http: HttpClient) {
  }

  getCitizenships(employeeId: number): Observable<Array<Citizenship>> {
    return this.http.get<Array<Citizenship>>(CITIZENSHIP_API + employeeId);
  }

  putCitizenship(formData: FormData): Observable<Citizenship> {
    return this.http.put<Citizenship>(CITIZENSHIP_API_MULTIPART, formData);
  }

}
