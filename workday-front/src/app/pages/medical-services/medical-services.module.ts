import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {MedicalServicesRoutes} from "./medical-services.routing";
import {MedicalServicesComponent} from "./medical-services.component";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(MedicalServicesRoutes),
    FormsModule
  ],
  exports: [RouterModule],
  declarations: [MedicalServicesComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class MedicalServicesModule {
}
