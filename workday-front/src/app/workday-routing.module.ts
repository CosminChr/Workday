import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ProfileModule} from "./pages/profile/profile.module";
import {CertificatesModule} from "./pages/certificates/certificates.module";
import {HolidaysModule} from "./pages/holidays/holidays.module";
import {MedicalServicesModule} from "./pages/medical-services/medical-services.module";
import {OvertimeModule} from "./pages/overtime/overtime.module";
import {WorkFromHomeModule} from "./pages/work-from-home/work-from-home.module";
import {CompanyJobsModule} from "./pages/company-jobs/company-jobs.module";


const routes: Routes = [

  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: '', redirectTo: 'register', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
    ProfileModule,
    CertificatesModule,
    HolidaysModule,
    MedicalServicesModule,
    OvertimeModule,
    WorkFromHomeModule,
    CompanyJobsModule
  ],
  exports: [RouterModule],
})
export class WorkdayRoutingModule {
}
