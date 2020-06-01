import {Referential} from "./referential.model";
import {Employee} from "./employee.model";
import {Partner} from "./partner.model";

export class MaritalStatus {

  id: number;

  employee: Employee;

  maritalStatus: Referential;

  startingDate: Date;
}

