import {Employee} from "./employee.model";
import {Referential} from "./referential.model";

export class Child {

  id: number;

  employee: Employee;

  lastName: string;

  firstName: string;

  personIdentifier: string;

  birthDate: Date;

  gender: Referential;

  worksInCompany: boolean;
}
