import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {WorkFromHomeComponent} from "./work-from-home/work-from-home.component";
import {WorkFromHomeRoutes} from "./work-from-home.routing";


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(WorkFromHomeRoutes),
    FormsModule
  ],
  declarations: [WorkFromHomeComponent]
})

export class WorkFromHomeModule {
}
