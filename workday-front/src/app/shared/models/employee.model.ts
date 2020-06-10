import {Referential} from "./referential.model";

export class Employee {

  id: number;

  username: string;

  email: string;

  password: string;

  lastName: string;

  firstName: string;

  gender: Referential;

  birthPlace: string;

  personIdentifier: string;

  birthDate: Date;

  birthName: string;

  homePhoneNumber: string;

  mobilePhoneNumber: string;

  jobPosition: Referential;

  entity: string;

  location: string;

  department: Referential;

  ITDeduction: boolean;

  joiningDate: Date;

  currentPositionStartingDate: Date;

  nationality: Referential;

  managerId: number;

  roles: Array<Referential>;
}
