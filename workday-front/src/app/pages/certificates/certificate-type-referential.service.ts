import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../shared/models/referential.model";

const CERTIFICATE_TYPE_REFERENTIAL_API = 'certificateTypeReferential/';


@Injectable({
  providedIn: 'root'
})
export class CertificateTypeReferentialService {

  constructor(private http: HttpClient) {
  }

  getCertificateTypeReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(CERTIFICATE_TYPE_REFERENTIAL_API);
  }

}
