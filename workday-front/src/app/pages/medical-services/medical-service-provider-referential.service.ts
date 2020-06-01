import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../shared/models/referential.model";

const MEDICAL_SERVICE_PROVIDER_REFERENTIAL_API = 'medicalServiceProviderReferential/';


@Injectable({
  providedIn: 'root'
})
export class MedicalServiceProviderReferentialService {

  constructor(private http: HttpClient) {
  }

  getMedicalServiceProviderReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(MEDICAL_SERVICE_PROVIDER_REFERENTIAL_API);
  }

}
