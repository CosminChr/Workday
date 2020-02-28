
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
              private router: Router) { }

  ngOnInit() {
  //  localStorage.clear();
    console.log(localStorage);
    this.workdayService.getIsConnected().asObservable().subscribe(value => {
      this.isConnected = value;
    });
    this.isConnected = !!this.tokenStorageService.getToken();

    if (this.isConnected) {
      // this.currentUser = this.tokenStorageService.getUser();
      this.employeeService.setStoredEmployee(this.tokenStorageService.getUser());
      //console.log("userul curent",this.employeeService.getSavedEmployee());
    //  this.router.navigate(['/personalData']);
      // this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      // this.showModeratorBoard = this.roles.includes('ROLE_MANAGER');
      //
      // this.username = user.username;
    } else {
      // console.log(" am navigat pe login");
      this.router.navigate(['/login']);
    }
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

  userIsConnected(isConnected: boolean) {
    this.isConnected = isConnected;
  }
}
