import { Routes } from '@angular/router';
import {MedicalServicesComponent} from "./medical-services/medical-services.component";


export const MedicalServicesRoutes: Routes = [{
  path: '',
  children: [{
    path: 'medicalServices',
    component: MedicalServicesComponent
  }]
}
];
