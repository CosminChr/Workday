import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {OvertimeRoutes} from "./overtime.routing";
import {OvertimeComponent} from "./overtime/overtime.component";

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(OvertimeRoutes),
    FormsModule
  ],
  declarations: [OvertimeComponent]
})

export class OvertimeModule {
}
