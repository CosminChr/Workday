import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HolidaysRoutes} from "./holidays.routing";
import {HolidaysComponent} from "./holidays.component";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(HolidaysRoutes),
    FormsModule
  ],
  exports: [RouterModule],
  declarations: [HolidaysComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class HolidaysModule {
}
