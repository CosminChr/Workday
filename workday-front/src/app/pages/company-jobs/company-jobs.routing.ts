import {Routes} from '@angular/router';
import {CompanyJobsComponent} from "./company-jobs.component";

export const CompanyJobsRoutes: Routes = [{

  path: '',
  children: [{
    path: '',
    component: CompanyJobsComponent
  }]
}];
