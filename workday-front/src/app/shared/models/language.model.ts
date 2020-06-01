import {Employee} from "./employee.model";
import {Referential} from "./referential.model";

export class Language {

  id: number;

  employee: Employee;

  language: Referential;

  reading: Referential;

  writing: Referential;

  speaking: Referential;

  overallLevel: Referential;

}
