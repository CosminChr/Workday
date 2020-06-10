import {Component, OnDestroy, OnInit, Output} from '@angular/core';
import {TokenStorageService} from "./core/services/security/token-storage.service";
import {Router} from "@angular/router";
import {EmployeeService} from "./shared/services/employee/employee.service";
import {WorkdayService} from "./workday.service";
import {Employee} from "./shared/models/employee.model";
import {Subject} from "rxjs";
import {takeUntil} from "rxjs/operators";
import {HolidaysMessagingService} from "./pages/holidays/holidays-messaging.service";

@Component({
  selector: 'workday-root',
  templateUrl: './workday.component.html',
  styleUrls: ['./workday.component.scss'],
})
export class WorkdayComponent implements OnInit, OnDestroy {

  employee: Employee;

  unsubscribe$ = new Subject<void>();

  @Output()
  isConnected = false;

  constructor(private tokenStorageService: TokenStorageService,
              private employeeService: EmployeeService,
              private workdayService: WorkdayService,
              private holidaysMessagingService: HolidaysMessagingService,
              private router: Router) {
  }

  ngOnInit() {

    this.workdayService.getStoredIsConnected().asObservable()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        data => {
          this.isConnected = data;
        });
    this.isConnected = !!this.tokenStorageService.getToken();

    if (this.isConnected) {
      this.employeeService.getEmployee(this.tokenStorageService.getUser().username).subscribe(data => {
        this.employee = data;
        this.employeeService.setStoredEmployee(this.employee);
      });
    } else {
      this.router.navigate(['/login']);
    }
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
