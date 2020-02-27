import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {WorkdayComponent} from "./workday.component";


const routes: Routes = [
  {
    path: '', component: WorkdayComponent,
    children: [{
      path: 'personalData',
      loadChildren: './pages/profile/profile.module#ProfileModule'
    }, {
      path: 'holidays',
      loadChildren: './pages/holidays/holidays.module#HolidaysModule'
    }, {
      path: 'certificates',
      loadChildren: './pages/certificates/certificates.module#CertificatesModule'
    }, {
      path: 'medicalServices',
      loadChildren: './pages/medical-services/medical-services.module#MedicalServicesModule'
    }, {
      path: 'workFromHome',
      loadChildren: './pages/work-from-home/work-from-home.module#WorkFromHomeModule'
    }, {
      path: 'overtime',
      loadChildren: './pages/overtime/overtime.module#OvertimeModule'
    }]
  },
  {path: 'login', component: LoginComponent},
  // {path: 'register', component: RegisterComponent},
  // {path: 'profile', component: ProfileComponent},
  // {path: 'user', component: BoardEmployeeComponent},
  // {path: 'mod', component: BoardManagerComponent},
  // {path: 'admin', component: BoardAdminComponent},
  // {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [RouterModule],
})
export class WorkdayRoutingModule {
}
