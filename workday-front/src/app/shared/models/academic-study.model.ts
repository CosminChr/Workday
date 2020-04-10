import {Employee} from "./employee.model";
import {Referential} from "./referential.model";

export class AcademicStudy {

  id: number;

  employee: Employee;

  studyLevel: Referential;

  educationalInstitution: string;

  studyField: Referential;

  specialization: string;

  country: Referential;

  fromDate: Date;

  toDate: Date;

  finalized: boolean;

}
