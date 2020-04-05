import {Referential} from "./referential.model";
import {Employee} from "./employee.model";
import {LocalityReferential} from "./locality.model";

export class Address {

  id: number;

  employee: Employee;

  addressType: Referential;

  street: string;

  number: string;

  block: string;

  stairwell: string;

  floor: number;

  apartmentNumber: number;

  locality: LocalityReferential;

  postalCode: string;

}
