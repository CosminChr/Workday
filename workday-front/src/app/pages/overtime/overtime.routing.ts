import { Routes } from '@angular/router';
import {OvertimeComponent} from "./overtime/overtime.component";


export const OvertimeRoutes: Routes = [{
  path: '',
  children: [{
    path: 'medicalServices',
    component: OvertimeComponent
  }]
}
];
