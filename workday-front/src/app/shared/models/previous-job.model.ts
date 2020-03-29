import {Employee} from "./employee.model";
import {Referential} from "./referential.model";
import {LocalityReferential} from "./locality.model";

export class PreviousJob {

  id: number;

  employee: Employee;

  employer: string;

  jobDomain: Referential;

  position: string;

  locality: LocalityReferential;

  fromDate: Date;

  toDate: Date;
}
