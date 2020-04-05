import {Employee} from "./employee.model";

export class Partner {

  id: number;

  employee: Employee;

  lastName: string;

  firstName: string;

  personIdentifier: string;

  birthDate: Date;

  worksInCompany: boolean;
}
