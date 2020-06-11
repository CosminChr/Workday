import {Employee} from "./employee.model";

export class Notification {

  id: number;

  message: string;

  employee: Employee;

  active: boolean;
}
