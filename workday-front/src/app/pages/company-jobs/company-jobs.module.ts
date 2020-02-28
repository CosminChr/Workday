import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {CompanyJobsRoutes} from "./company-jobs.routing";
import {CompanyJobsComponent} from "./company-jobs/company-jobs.component";



@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(CompanyJobsRoutes),
    FormsModule
  ],
  declarations: [CompanyJobsComponent]
})

export class CompanyJobsModule {
}
