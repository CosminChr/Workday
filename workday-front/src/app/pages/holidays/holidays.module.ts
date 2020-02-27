import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {HolidaysRoutes} from "./holidays.routing";
import { HolidaysComponent } from './holidays/holidays.component';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(HolidaysRoutes),
    FormsModule
  ],
  declarations: [HolidaysComponent]
})

export class HolidaysModule {}
