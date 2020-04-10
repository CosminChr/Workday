import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const STUDY_FIELD_REFERENTIAL_API = 'studyFieldReferential/';


@Injectable({
  providedIn: 'root'
})
export class StudyFieldReferentialService {

  constructor(private http: HttpClient) {
  }

  getStudyFieldReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(STUDY_FIELD_REFERENTIAL_API);
  }

}
