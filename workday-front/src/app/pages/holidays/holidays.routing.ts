import { Routes } from '@angular/router';
import {HolidaysComponent} from "./holidays/holidays.component";

export const HolidaysRoutes: Routes = [{
  path: '',
  children: [{
    path: 'holidays',
    component: HolidaysComponent
  }]
}
];
