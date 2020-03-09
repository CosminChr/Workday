import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RegisterRoutes} from "./register.routing";
import {RegisterComponent} from "./register.component";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(RegisterRoutes),
    FormsModule
  ],
  exports: [RouterModule],
  declarations: [RegisterComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class RegisterModule {
}
