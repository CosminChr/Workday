import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { WorkdayRoutingModule } from './workday-routing.module';
import { WorkdayComponent } from './workday.component';

@NgModule({
  declarations: [
    WorkdayComponent
  ],
  imports: [
    BrowserModule,
    WorkdayRoutingModule
  ],
  providers: [],
  bootstrap: [WorkdayComponent]
})
export class WorkdayModule { }
