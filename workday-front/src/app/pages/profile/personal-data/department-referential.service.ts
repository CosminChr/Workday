import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const DEPARTMENT_REFERENTIAL_API = 'departmentReferential/';


@Injectable({
  providedIn: 'root'
})
export class DepartmentReferentialService {

  constructor(private http: HttpClient) {
  }

  getDepartmentReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(DEPARTMENT_REFERENTIAL_API);
  }

}
