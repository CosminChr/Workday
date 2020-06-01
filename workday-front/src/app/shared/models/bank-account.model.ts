import {Referential} from "./referential.model";
import {Employee} from "./employee.model";
import {LocalityReferential} from "./locality.model";

export class BankAccount {

  id: number;

  employee: Employee;

  bank: string;

  agency: string;

  iban: string;

  expirationDate: Date;

  currency: Referential;

  primaryAccount: boolean;

  attestingDocument: any;

}
