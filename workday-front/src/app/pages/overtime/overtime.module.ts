import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {OvertimeRoutes} from "./overtime.routing";
import {OvertimeComponent} from "./overtime.component";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(OvertimeRoutes),
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [RouterModule],
  declarations: [OvertimeComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class OvertimeModule {
}
