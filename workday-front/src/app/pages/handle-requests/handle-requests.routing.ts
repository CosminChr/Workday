import {Routes} from '@angular/router';
import {HandleRequestsComponent} from "./handle-requests.component";

export const HandleRequestsRoutes: Routes = [{

  path: '',
  children: [{
    path: '',
    component: HandleRequestsComponent
  }]
}];
