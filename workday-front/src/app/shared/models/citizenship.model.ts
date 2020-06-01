import {Employee} from "./employee.model";
import {Referential} from "./referential.model";

export class Citizenship {

  id: number;

  employee: Employee;

  citizenship: Referential;
}
