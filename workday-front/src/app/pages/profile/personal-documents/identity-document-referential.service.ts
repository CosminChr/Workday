import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const IDENTITY_DOCUMENT_TYPE_REFERENTIAL_API = 'identityDocumentTypeReferential/';


@Injectable({
  providedIn: 'root'
})
export class IdentityDocumentReferentialService {

  constructor(private http: HttpClient) {
  }

  getIdentityDocumentTypeReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(IDENTITY_DOCUMENT_TYPE_REFERENTIAL_API);
  }

}
