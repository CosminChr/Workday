// import {Component, OnInit} from '@angular/core';
// import {TokenStorageService} from "./core/services/security/token-storage.service";
//
// @Component({
//   selector: 'workday-root',
//   templateUrl: './workday.component.html',
//   styleUrls: ['./workday.component.scss']
// })
// export class WorkdayComponent implements OnInit {
//   private roles: string[];
//   isLoggedIn = false;
//   showAdminBoard = false;
//   showModeratorBoard = false;
//   username: string;
//
//   constructor(private tokenStorageService: TokenStorageService) { }
//
//   ngOnInit() {
//     this.isLoggedIn = !!this.tokenStorageService.getToken();
//
//     if (this.isLoggedIn) {
//       const user = this.tokenStorageService.getUser();
//       this.roles = user.roles;
//
//       this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
//       this.showModeratorBoard = this.roles.includes('ROLE_MANAGER');
//
//       this.username = user.username;
//     }
//   }
//
//   logout() {
//     this.tokenStorageService.signOut();
//     window.location.reload();
//   }
// }

import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "./core/services/security/token-storage.service";
import {Router} from "@angular/router";
import {EmployeeService} from "./shared/services/employee/employee.service";
import {WorkdayService} from "./workday.service";

@Component({
  selector: 'workday-root',
  templateUrl: './workday.component.html',
  styleUrls: ['./workday.component.scss'],
})
export class WorkdayComponent implements OnInit {

  isConnected = false;

  constructor(private tokenStorageService: TokenStorageService,
              private employeeService: EmployeeService,
              private workdayService: WorkdayService,
              private router: Router) {
  }

  ngOnInit() {
  //  localStorage.clear();

   this.workdayService.getStoredIsConnected().asObservable()
     .subscribe(
     data => {
       this.isConnected = data;
     });
    this.isConnected = !!this.tokenStorageService.getToken();

    if (this.isConnected) {
      this.router.navigate(['/profile/personalData']);
    } else {
      this.router.navigate(['/login']);
    }
  }

  logout() {
    this.tokenStorageService.signOut();
  }
}
