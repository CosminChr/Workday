import {Referential} from "./referential.model";
import {Employee} from "./employee.model";

export class Holiday {

  id: number;

  employee: Employee;

  holidayType: Referential;

  fromDate: Date;

  toDate: Date;

  approved: boolean;

  validated: boolean;

  comment: string;

}
