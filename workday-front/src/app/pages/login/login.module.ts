import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoginRoutes} from "./login.routing";
import {LoginComponent} from "./login.component";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(LoginRoutes),
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [RouterModule],
  declarations: [LoginComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class LoginModule {
}
