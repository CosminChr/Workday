import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {LocalityReferential} from "../../../shared/models/locality.model";

const LOCALITY_REFERENTIAL_API = 'localityReferential/';


@Injectable({
  providedIn: 'root'
})
export class LocalityReferentialService {

  constructor(private http: HttpClient) {
  }

  getLocalityReferentials(): Observable<Array<LocalityReferential>> {
    return this.http.get<Array<LocalityReferential>>(LOCALITY_REFERENTIAL_API);
  }

}
