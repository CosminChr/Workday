import {Employee} from "./employee.model";
import {Referential} from "./referential.model";

export class CompanyJob {

  id: number;

  jobTitle: string;

  jobField: Referential;

  locality: string;

  country: string;

  requiredExperience: Referential;

  contractType: Referential;

  postingDate: Date;

  selected: boolean;
}
