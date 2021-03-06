import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Language} from "../../../shared/models/language.model";
import {AcademicStudy} from "../../../shared/models/academic-study.model";

const ACADEMIC_STUDY_API = 'academicStudy/';

const ACADEMIC_STUDY_API_MULTIPART = 'academicStudy/multipart/diploma';


@Injectable({
  providedIn: 'root'
})
export class AcademicStudyService {

  constructor(private http: HttpClient) {
  }

  getStudies(employeeId: number): Observable<Array<AcademicStudy>> {
    return this.http.get<Array<AcademicStudy>>(ACADEMIC_STUDY_API + employeeId);
  }

  putStudy(formData: FormData): Observable<AcademicStudy> {
    return this.http.put<AcademicStudy>(ACADEMIC_STUDY_API_MULTIPART, formData);
  }

}
