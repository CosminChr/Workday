import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HandleRequestsComponent} from "./handle-requests.component";
import {HandleRequestsRoutes} from "./handle-requests.routing";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(HandleRequestsRoutes),
    FormsModule
  ],
  exports: [RouterModule],
  declarations: [HandleRequestsComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class HandleRequestsModule {
}
