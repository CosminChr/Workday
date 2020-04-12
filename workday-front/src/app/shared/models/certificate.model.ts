import {Referential} from "./referential.model";
import {Employee} from "./employee.model";

export class Certificate {

  id: number;

  employee: Employee;

  certificateType: Referential;

  generationDate: Date;

}
