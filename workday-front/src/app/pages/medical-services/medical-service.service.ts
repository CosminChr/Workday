import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MedicalService} from "../../shared/models/medical-service.model";

const MEDICAL_SERVICE_API = 'medicalService/';


@Injectable({
  providedIn: 'root'
})
export class MedicalServiceService {

  constructor(private http: HttpClient) {
  }

  getMedicalService(employeeId: number): Observable<MedicalService> {
    return this.http.get<MedicalService>(MEDICAL_SERVICE_API + employeeId);
  }

  putMedicalService(medicalService: MedicalService): Observable<MedicalService> {
    return this.http.put<MedicalService>(MEDICAL_SERVICE_API, medicalService);
  }

}
