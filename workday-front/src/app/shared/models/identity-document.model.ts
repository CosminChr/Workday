import {Referential} from "./referential.model";
import {Employee} from "./employee.model";

export class IdentityDocument {

  id: number;

  employee: Employee;

  identityDocumentType: Referential;

  seriesAndNumber: String;

  issueDate: Date;

  expirationDate: Date;

  issuer: string;

  country: Referential;

  description: string;

}
