import { Routes } from '@angular/router';
import {WorkFromHomeComponent} from "./work-from-home/work-from-home.component";

export const WorkFromHomeRoutes: Routes = [{
  path: '',
  children: [{
    path: 'certificates',
    component: WorkFromHomeComponent
  }]
}
];
