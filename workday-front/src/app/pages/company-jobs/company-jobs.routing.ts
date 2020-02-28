import { Routes } from '@angular/router';
import {CompanyJobsComponent} from "./company-jobs/company-jobs.component";

export const CompanyJobsRoutes: Routes = [{
  path: '',
  children: [{
    path: 'companyJobs',
    component: CompanyJobsComponent
  }]
}
];
