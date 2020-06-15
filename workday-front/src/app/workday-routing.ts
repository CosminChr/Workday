import {Routes} from '@angular/router';
import {WorkdayComponent} from "./workday.component";


export const WorkdayRoutes: Routes = [{
  path: '',
  redirectTo: 'profile/personalData',
  pathMatch: 'full',
}, {
  path: '',
  component: WorkdayComponent,
  children: [{
    path: 'certificates',
    loadChildren: './pages/certificates/certificates.module#CertificatesModule'
  }, {
    path: 'companyJobs',
    loadChildren: './pages/company-jobs/company-jobs.module#CompanyJobsModule'
  }, {
    path: 'handleRequests',
    loadChildren: './pages/handle-requests/handle-requests.module#HandleRequestsModule'
  }, {
    path: 'holidays',
    loadChildren: './pages/holidays/holidays.module#HolidaysModule'
  }, {
    path: 'medicalServices',
    loadChildren: './pages/medical-services/medical-services.module#MedicalServicesModule'
  }, {
    path: 'overtime',
    loadChildren: './pages/overtime/overtime.module#OvertimeModule'
  }, {
    path: 'profile',
    loadChildren: './pages/profile/profile.module#ProfileModule'
  }, {
    path: 'workFromHome',
    loadChildren: './pages/work-from-home/work-from-home.module#WorkFromHomeModule'
  }, {
    path: 'login',
    loadChildren: './pages/login/login.module#LoginModule'
  }, {
    path: 'register',
    loadChildren: './pages/register/register.module#RegisterModule'
  }, {
    path: 'employees',
    loadChildren: './pages/employees/employees.module#EmployeesModule'
  }, {
    path: 'reports',
    loadChildren: './pages/reports/reports.module#ReportsModule'
  },
  ]
}
];
