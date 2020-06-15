import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ReportsComponent} from "./reports.component";
import {ReportsRoutes} from "./reports.routing";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(ReportsRoutes),
    FormsModule
  ],
  exports: [RouterModule],
  declarations: [ReportsComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class ReportsModule {
}
