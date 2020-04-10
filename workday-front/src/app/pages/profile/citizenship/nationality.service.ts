import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const NATIONALITY_API = 'nationality/';


@Injectable({
  providedIn: 'root'
})
export class NationalityService {

  constructor(private http: HttpClient) {
  }

  getnationality(employeeId: number): Observable<Referential> {
    return this.http.get<Referential>(NATIONALITY_API + employeeId);
  }

  putNationality(nationality: Referential, employeeId: number): Observable<Referential> {
    return this.http.put<Referential>(NATIONALITY_API + employeeId, nationality);
  }

}
