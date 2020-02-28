import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {CertificatesRoutes} from "./certificates.routing";
import {CertificatesComponent} from "./certificates/certificates.component";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(CertificatesRoutes),
    FormsModule
  ],
  declarations: [CertificatesComponent]
})

export class CertificatesModule {
}
