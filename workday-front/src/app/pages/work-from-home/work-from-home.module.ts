import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {WorkFromHomeRoutes} from "./work-from-home.routing";
import {WorkFromHomeComponent} from "./work-from-home.component";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(WorkFromHomeRoutes),
    FormsModule
  ],
  exports: [RouterModule],
  declarations: [WorkFromHomeComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class WorkFromHomeModule {
}
