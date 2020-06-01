import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Language} from "../../../shared/models/language.model";

const LANGUAGE_API = 'language/';

const LANGUAGE_API_MULTIPART = 'language/multipart/languageCertification/';


@Injectable({
  providedIn: 'root'
})
export class LanguageService {

  constructor(private http: HttpClient) {
  }

  getLanguages(employeeId: number): Observable<Array<Language>> {
    return this.http.get<Array<Language>>(LANGUAGE_API + employeeId);
  }

  putLanguage(formData: FormData): Observable<Language> {
    return this.http.put<Language>(LANGUAGE_API_MULTIPART, formData);
  }

}
