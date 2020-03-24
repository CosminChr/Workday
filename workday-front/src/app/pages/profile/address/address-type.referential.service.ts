import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Referential} from "../../../shared/models/referential.model";

const ADDRESS_TYPE_REFERENTIAL_REFERENTIAL_API = 'addressTypeReferential/';


@Injectable({
  providedIn: 'root'
})
export class AddressTypeReferentialService {

  constructor(private http: HttpClient) {
  }

  getAddressTypeReferentials(): Observable<Array<Referential>> {
    return this.http.get<Array<Referential>>(ADDRESS_TYPE_REFERENTIAL_REFERENTIAL_API);
  }

}
