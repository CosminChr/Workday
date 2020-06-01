import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const LANGUAGE_REFERENTIAL_API = 'languageReferential/';


@Injectable({
  providedIn: 'root'
})
export class LanguageReferentialService {

  constructor(private http: HttpClient) {
  }

  getLanguageReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(LANGUAGE_REFERENTIAL_API);
  }

}
