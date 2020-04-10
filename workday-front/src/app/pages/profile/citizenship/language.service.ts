import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Citizenship} from "../../../shared/models/citizenship.model";
import {Language} from "../../../shared/models/language.model";

const LANGUAGE_API = 'language/';


@Injectable({
  providedIn: 'root'
})
export class LanguageService {

  constructor(private http: HttpClient) {
  }

  getLanguages(employeeId: number): Observable<Array<Language>> {
    return this.http.get<Array<Language>>(LANGUAGE_API + employeeId);
  }

  putLanguage(language: Language): Observable<Language> {
    return this.http.put<Language>(LANGUAGE_API, language);
  }

}
