import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ProfileRoutes} from "./profile.routing";
import {PersonalDataComponent} from './personal-data/personal-data.component';
import {AddressComponent} from './address/address.component';
import {BankAccountComponent} from './bank-account/bank-account.component';
import {PersonalDocumentsComponent} from './personal-documents/personal-documents.component';
import {EmploymentHistoryComponent} from './employment-history/employment-history.component';
import {MaritalStatusComponent} from './marital-status/marital-status.component';
import {CitizenshipComponent} from './citizenship/citizenship.component';
import {StudiesComponent} from './studies/studies.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {ProfileComponent} from "./profile.component";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(ProfileRoutes),
    FormsModule,
    NgbModule,
    ReactiveFormsModule
  ],
  exports: [RouterModule],
  declarations: [PersonalDataComponent,
    AddressComponent,
    BankAccountComponent,
    PersonalDocumentsComponent,
    EmploymentHistoryComponent,
    MaritalStatusComponent,
    CitizenshipComponent,
    StudiesComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class ProfileModule {
}
