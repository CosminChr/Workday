import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BankAccount} from "../../../shared/models/bank-account.model";
import {IdentityDocument} from "../../../shared/models/identity-document.model";

const IDENTITY_DOCUMENT_API = 'identityDocument/';


@Injectable({
  providedIn: 'root'
})
export class IdentityDocumentService {

  constructor(private http: HttpClient) {
  }

  getIdentityDocuments(employeeId: number): Observable<Array<IdentityDocument>> {
    return this.http.get<Array<IdentityDocument>>(IDENTITY_DOCUMENT_API + employeeId);
  }

  putIdentityDocument(identityDocument: IdentityDocument): Observable<IdentityDocument> {
    return this.http.put<IdentityDocument>(IDENTITY_DOCUMENT_API, identityDocument);
  }
}
