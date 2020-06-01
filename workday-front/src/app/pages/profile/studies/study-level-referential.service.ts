import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const STUDY_LEVEL_REFERENTIAL_API = 'studyLevelReferential/';


@Injectable({
  providedIn: 'root'
})
export class StudyLevelReferentialService {

  constructor(private http: HttpClient) {
  }

  getStudyLevelReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(STUDY_LEVEL_REFERENTIAL_API);
  }

}
