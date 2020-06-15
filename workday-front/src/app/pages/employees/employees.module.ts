import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {EmployeesRoutes} from "./employees.routing";
import {EmployeesComponent} from "./employees.component";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(EmployeesRoutes),
    FormsModule
  ],
  exports: [RouterModule],
  declarations: [EmployeesComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class EmployeesModule {
}
