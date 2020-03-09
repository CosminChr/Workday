import { Routes } from '@angular/router';
import {CertificatesComponent} from "./certificates.component";

export const CertificatesRoutes: Routes = [{

  path: '',
  children: [ {
    path: '',
    component: CertificatesComponent
  }]
}];
