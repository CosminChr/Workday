import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BankAccount} from "../../../shared/models/bank-account.model";

const BANK_ACCOUNT_API = 'bankAccount/';


@Injectable({
  providedIn: 'root'
})
export class BankAccountService {

  constructor(private http: HttpClient) {
  }

  getBankAccounts(employeeId: number): Observable<Array<BankAccount>> {
    return this.http.get<Array<BankAccount>>(BANK_ACCOUNT_API + employeeId);
  }

  putBankAccount(bankAccount: BankAccount): Observable<BankAccount> {
    return this.http.put<BankAccount>(BANK_ACCOUNT_API, bankAccount);
  }
}
