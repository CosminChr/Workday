import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';

import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import { WorkdayRoutingModule } from './workday-routing.module';
import { WorkdayComponent } from './workday.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { BoardAdminComponent } from './pages/board-admin/board-admin.component';
import { BoardManagerComponent } from './pages/board-manager/board-manager.component';
import { BoardEmployeeComponent } from './pages/board-employee/board-employee.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {
  MatDatepickerModule,
  MatFormFieldModule,
  MatNativeDateModule,
  MatProgressSpinnerModule, MatTabsModule
} from "@angular/material";
import {MaterialModule} from "./material.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { SidebarComponent } from './shared/components/sidebar/sidebar.component';
import {CompanyJobsComponent} from "./pages/company-jobs/company-jobs/company-jobs.component";

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    WorkdayComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    BoardAdminComponent,
    BoardManagerComponent,
    BoardEmployeeComponent,
    NavbarComponent,
    SidebarComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    WorkdayRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MaterialModule,
    MatNativeDateModule,
    BrowserAnimationsModule,
    MatProgressSpinnerModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    MatTabsModule

  ],
  providers: [],
  bootstrap: [WorkdayComponent]
})
export class WorkdayModule { }
