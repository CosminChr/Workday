import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Address} from "../../../shared/models/address.model";

const ADDRESS_API = 'address/';


@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http: HttpClient) {
  }

  getAddresses(employeeId: number): Observable<Array<Address>> {
    return this.http.get<Array<Address>>(ADDRESS_API + employeeId);
  }

  putAddress(address: Address): Observable<Address> {
    return this.http.put<Address>(ADDRESS_API, address);
  }

}
