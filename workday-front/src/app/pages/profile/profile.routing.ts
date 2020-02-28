import { Routes } from '@angular/router';
import {PersonalDataComponent} from "./personal-data/personal-data.component";
import {AddressComponent} from "./address/address.component";
import {BankAccountComponent} from "./bank-account/bank-account.component";
import {PersonalDocumentsComponent} from "./personal-documents/personal-documents.component";
import {EmploymentHistoryComponent} from "./employment-history/employment-history.component";
import {MaritalStatusComponent} from "./marital-status/marital-status.component";
import {CitizenshipComponent} from "./citizenship/citizenship.component";
import {StudiesComponent} from "./studies/studies.component";


export const ProfileRoutes: Routes = [{
  path: '',
  children: [{
    path: 'personalData',
    component: PersonalDataComponent
  }]
},{
  path: '',
  children: [{
    path: 'address',
    component: AddressComponent
  }]
},{
  path: '',
  children: [{
    path: 'bankAccount',
    component: BankAccountComponent
  }]
},{
  path: '',
  children: [{
    path: 'personalDocuments',
    component: PersonalDocumentsComponent
  }]
},{
  path: '',
  children: [{
    path: 'employmentHistory',
    component: EmploymentHistoryComponent
  }]
},{
  path: '',
  children: [{
    path: 'maritalStatus',
    component: MaritalStatusComponent
  }]
},{
  path: '',
  children: [{
    path: 'citizenship',
    component: CitizenshipComponent
  }]
},{
  path: '',
  children: [{
    path: 'studies',
    component: StudiesComponent
  }]
}
];
