import {Employee} from "./employee.model";
import {Referential} from "./referential.model";

export class Overtime {

  id: number;

  employee: Employee;

  numberOfHours: number;

  effectuationDate: Date;

  initiationDate: Date;

  approved: boolean;

  validate: boolean;
}
