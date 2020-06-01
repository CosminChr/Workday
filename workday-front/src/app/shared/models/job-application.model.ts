import {CompanyJob} from "./company-job.model";
import {Employee} from "./employee.model";

export class JobApplication {

  id: number;

  companyJob: CompanyJob;

  submittedBy: Employee;

  cv: any;
}
