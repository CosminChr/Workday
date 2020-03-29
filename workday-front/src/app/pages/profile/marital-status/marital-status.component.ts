import { Component, OnInit } from '@angular/core';
import {FormGroup} from "@angular/forms";
import {Employee} from "../../../shared/models/employee.model";
import {Referential} from "../../../shared/models/referential.model";
import {IdentityDocument} from "../../../shared/models/identity-document.model";
import {MaritalStatus} from "../../../shared/models/marital-status.model";
import {Child} from "../../../shared/models/child.model";

@Component({
  selector: 'workday-marital-status',
  templateUrl: './marital-status.component.html',
  styleUrls: ['./marital-status.component.scss']
})
export class MaritalStatusComponent implements OnInit {

  employee: Employee;

  maritalStatusReferentials: Array<Referential>;

  identityDocuments: Array<IdentityDocument>;

  maritalStatus: MaritalStatus;

  maritalStatusFormGroup: FormGroup;

  children: Array<Child>;

  newChild = new Child();

  childrenFormGroups: Array<FormGroup>;

  newChildFormGroup: FormGroup;

  constructor() { }

  ngOnInit() {
  }

}
