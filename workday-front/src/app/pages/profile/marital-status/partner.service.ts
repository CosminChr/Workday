import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Partner} from "../../../shared/models/partner.model";

const PARTNER_API = 'partner/';


@Injectable({
  providedIn: 'root'
})
export class PartnerService {

  constructor(private http: HttpClient) {
  }

  getPartner(employeeId: number): Observable<Partner> {
    return this.http.get<Partner>(PARTNER_API + employeeId);
  }

  putPartner(partner: Partner): Observable<Partner> {
    return this.http.put<Partner>(PARTNER_API, partner);
  }

}
