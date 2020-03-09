import {BrowserModule} from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';

import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {WorkdayRoutes} from './workday-routing';
import {WorkdayComponent} from './workday.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {
  MatDatepickerModule,
  MatFormFieldModule,
  MatNativeDateModule,
  MatProgressSpinnerModule,
  MatTabsModule
} from "@angular/material";
import {MaterialModule} from "./material.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {SidebarComponent} from "./shared/components/sidebar/sidebar.component";

import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {RouterModule} from "@angular/router";
import {HttpModule} from "@angular/http";

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    WorkdayComponent,
    SidebarComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  imports: [
    RouterModule.forRoot(WorkdayRoutes, {
      useHash: true
    }),
    BrowserModule,
    HttpClientModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MaterialModule,
    MatNativeDateModule,
    BrowserAnimationsModule,
    MatProgressSpinnerModule,
    NgbModule.forRoot(),

    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    MatTabsModule
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [WorkdayComponent]
})
export class WorkdayModule {
}
