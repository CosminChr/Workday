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

import {ViewEncapsulation} from '@angular/core';

import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "./core/services/security/token-storage.service";

@Component({
  selector: 'workday-root',
  templateUrl: './workday.component.html',
  styleUrls: ['./workday.component.scss'],
})
export class WorkdayComponent implements OnInit {

  isLoggedIn = false;
  currentUser: any;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log(this.tokenStorageService.getToken());
    if (this.isLoggedIn) {
      this.currentUser = this.tokenStorageService.getUser();
      console.log(this.currentUser);

      // this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      // this.showModeratorBoard = this.roles.includes('ROLE_MANAGER');
      //
      // this.username = user.username;
    }
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
