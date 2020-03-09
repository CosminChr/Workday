import {Routes} from '@angular/router';
import {HolidaysComponent} from "./holidays.component";

export const HolidaysRoutes: Routes = [{

  path: '',
  children: [{
    path: '',
    component: HolidaysComponent
  }]
}];
