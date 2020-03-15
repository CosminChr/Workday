import {BrowserModule} from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';

import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {WorkdayRoutes} from './workday-routing';
import {WorkdayComponent} from './workday.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {SidebarComponent} from "./shared/components/sidebar/sidebar.component";

import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {RouterModule} from "@angular/router";
import {HttpModule} from "@angular/http";
import {apiInterceptorProvider} from "./core/interceptors/api.interceptor";
import {authInterceptorProvider} from "./core/interceptors/auth.interceptor";
import {NavbarComponent} from "./shared/components/navbar/navbar.component";

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    WorkdayComponent,
    SidebarComponent,
    NavbarComponent
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
    BrowserAnimationsModule,
    NgbModule,

    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  exports: [RouterModule],
  providers: [authInterceptorProvider,
  apiInterceptorProvider],
  bootstrap: [WorkdayComponent]
})
export class WorkdayModule {
}
