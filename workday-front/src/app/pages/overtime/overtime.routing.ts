import {Routes} from '@angular/router';
import {OvertimeComponent} from "./overtime.component";

export const OvertimeRoutes: Routes = [{

  path: '',
  children: [{
    path: '',
    component: OvertimeComponent
  }]
}];
