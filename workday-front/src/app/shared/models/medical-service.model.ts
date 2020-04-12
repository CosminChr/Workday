import {Referential} from "./referential.model";
import {Employee} from "./employee.model";

export class MedicalService {

  id: number;

  employee: Employee;

  medicalServiceProvider: Referential;

  initiationDate: Date;

}
