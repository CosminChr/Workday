import {Referential} from "./referential.model";

export class Admin {

  id: number;

  username: string;

  email: string;

  password: string;

  role: Referential;
}
