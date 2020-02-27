import { Routes } from '@angular/router';
import {CertificatesComponent} from "./certificates/certificates.component";

export const CertificatesRoutes: Routes = [{
  path: '',
  children: [{
    path: 'certificates',
    component: CertificatesComponent
  }]
}
];
