import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import { CompanyJobsComponent } from './company-jobs.component';
import {CompanyJobsRoutes} from "./company-jobs.routing";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(CompanyJobsRoutes),
    FormsModule
  ],
  exports: [RouterModule],
  declarations: [CompanyJobsComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class CompanyJobsModule {
}
