import {Employee} from "./employee.model";
import {Referential} from "./referential.model";

export class WorkFromHome {

  id: number;

  employee: Employee;

  startDateDay1: Date;

  startDateDay2: Date;

  dayOfWeekDay1: Referential;

  dayOfWeekDay2: Referential;

  potentialDayOfWeekDay1: Referential;

  potentialDayOfWeekDay2: Referential;

  lastInitiationDate: Date;

  lastApprovalDate: Date;
}
