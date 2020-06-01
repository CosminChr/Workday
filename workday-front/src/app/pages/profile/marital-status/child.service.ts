import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Child} from "../../../shared/models/child.model";

const CHILD_API = 'child/';

const CHILD_API_MULTIPART = 'child/multipart/birthCertificate/';


@Injectable({
  providedIn: 'root'
})
export class ChildService {


  constructor(private http: HttpClient) {
  }


  getChildren(employeeId: number): Observable<Array<Child>> {
    return this.http.get<Array<Child>>(CHILD_API + employeeId);
  }

  putChild(formData: FormData): Observable<Child> {
    return this.http.put<Child>(CHILD_API_MULTIPART, formData);
  }

}
