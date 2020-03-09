import {Routes} from '@angular/router';
import {WorkFromHomeComponent} from "./work-from-home.component";

export const WorkFromHomeRoutes: Routes = [{

  path: '',
  children: [{
    path: '',
    component: WorkFromHomeComponent
  }]
}];
