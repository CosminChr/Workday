import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MedicalService} from "../../shared/models/medical-service.model";
import {Certificate} from "../../shared/models/certificate.model";

const CERTIFICATE_API = 'certificate/';


@Injectable({
  providedIn: 'root'
})
export class CertificateService {

  constructor(private http: HttpClient) {
  }

  getCertificates(employeeId: number): Observable<Array<Certificate>> {
    return this.http.get<Array<Certificate>>(CERTIFICATE_API + employeeId);
  }

  putCertificate(certificate: Certificate): Observable<Certificate> {
    return this.http.put<Certificate>(CERTIFICATE_API, certificate);
  }

}
