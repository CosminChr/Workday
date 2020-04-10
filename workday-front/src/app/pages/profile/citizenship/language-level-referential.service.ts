import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const LANGUAGE_LEVEL_REFERENTIAL_API = 'languageLevelReferential/';


@Injectable({
  providedIn: 'root'
})
export class LanguageLevelReferentialService {

  constructor(private http: HttpClient) {
  }

  getLanguageLevelReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(LANGUAGE_LEVEL_REFERENTIAL_API);
  }

}
