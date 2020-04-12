import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CertificatesComponent} from "./certificates.component";
import {CertificatesRoutes} from "./certificates.routing";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(CertificatesRoutes),
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [RouterModule],
  declarations: [CertificatesComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class CertificatesModule {
}
