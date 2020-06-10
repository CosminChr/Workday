import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../models/referential.model";

const ROLE_API = 'roleReferential/';


@Injectable({
  providedIn: 'root'
})
export class RoleReferentialService {

  constructor(private http: HttpClient) {
  }

  getRoleReferentialsForEmployee(employeeId: number): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(ROLE_API + employeeId);
  }

}
