import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {ProfileComponent} from "./pages/profile/profile.component";
import {BoardEmployeeComponent} from "./pages/board-employee/board-employee.component";
import {BoardManagerComponent} from "./pages/board-manager/board-manager.component";
import {BoardAdminComponent} from "./pages/board-admin/board-admin.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardEmployeeComponent },
  { path: 'mod', component: BoardManagerComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [RouterModule],
})
export class WorkdayRoutingModule { }
