import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {MedicalServicesRoutes} from "./medical-services.routing";
import {MedicalServicesComponent} from "./medical-services/medical-services.component";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(MedicalServicesRoutes),
    FormsModule
  ],
  declarations: [MedicalServicesComponent]
})

export class MedicalServicesModule {
}
