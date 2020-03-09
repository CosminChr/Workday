import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {CertificatesComponent} from "./certificates.component";
import {CertificatesRoutes} from "./certificates.routing";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(CertificatesRoutes),
    FormsModule
  ],
  exports: [RouterModule],
  declarations: [CertificatesComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class CertificatesModule {
}
