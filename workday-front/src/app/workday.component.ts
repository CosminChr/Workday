import {Component, OnInit, Output} from '@angular/core';
import {TokenStorageService} from "./core/services/security/token-storage.service";
import {Router} from "@angular/router";
import {EmployeeService} from "./shared/services/employee/employee.service";
import {WorkdayService} from "./workday.service";
import {Employee} from "./shared/models/employee.model";

@Component({
  selector: 'workday-root',
  templateUrl: './workday.component.html',
  styleUrls: ['./workday.component.scss'],
})
export class WorkdayComponent implements OnInit {

  employee: Employee;

  @Output()
  isConnected = false;

  constructor(private tokenStorageService: TokenStorageService,
              private employeeService: EmployeeService,
              private workdayService: WorkdayService,
              private router: Router) {
  }

  ngOnInit() {


    this.workdayService.getStoredIsConnected().asObservable()
      .subscribe(
        data => {
          this.isConnected = data;
        });
    this.isConnected = !!this.tokenStorageService.getToken();

    if (this.isConnected) {
     // this.router.navigate(['/profile/personalData']);
      this.employeeService.getEmployee(this.tokenStorageService.getUser().username).subscribe( data => {
        this.employee = data;
        this.employeeService.setStoredEmployee(this.employee);
      });
    } else {
      this.router.navigate(['/login']);
    }
  }
}
