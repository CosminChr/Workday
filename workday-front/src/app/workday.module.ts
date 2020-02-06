import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { WorkdayRoutingModule } from './workday-routing.module';
import { WorkdayComponent } from './workday.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { BoardAdminComponent } from './pages/board-admin/board-admin.component';
import { BoardManagerComponent } from './pages/board-manager/board-manager.component';
import { BoardEmployeeComponent } from './pages/board-employee/board-employee.component';

@NgModule({
  declarations: [
    WorkdayComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardManagerComponent,
    BoardEmployeeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    WorkdayRoutingModule
  ],
  providers: [],
  bootstrap: [WorkdayComponent]
})
export class WorkdayModule { }
