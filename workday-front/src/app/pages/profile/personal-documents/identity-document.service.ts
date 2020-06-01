import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {IdentityDocument} from "../../../shared/models/identity-document.model";

const IDENTITY_DOCUMENT_API = 'identityDocument/';

const IDENTITY_DOCUMENT_API_MULTIPART = 'identityDocument/multipart/document/';


@Injectable({
  providedIn: 'root'
})
export class IdentityDocumentService {

  constructor(private http: HttpClient) {
  }

  getIdentityDocuments(employeeId: number): Observable<Array<IdentityDocument>> {
    return this.http.get<Array<IdentityDocument>>(IDENTITY_DOCUMENT_API + employeeId);
  }

  putIdentityDocument(formData: FormData): Observable<IdentityDocument> {
    return this.http.put<IdentityDocument>(IDENTITY_DOCUMENT_API_MULTIPART, formData);
  }
}
