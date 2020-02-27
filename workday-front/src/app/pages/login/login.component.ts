import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../core/services/security/auth.service';
import {TokenStorageService} from '../../core/services/security/token-storage.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {WorkdayService} from "../../workday.service";

@Component({
  selector: 'workday-login',
  templateUrl: './login.component.html',
  styleUrls: []
})
export class LoginComponent implements OnInit {

  isLoginFailed = false;

  errorMessage = '';

  username: string;

  password: string;

  isSubmitted = false;
  //roles: string[] = [];


  loginForm: FormGroup;

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private employeeService: EmployeeService,
              private workDayService: WorkdayService,
              private formBuilder: FormBuilder,
              private router: Router) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {

      this.workDayService.setIsConnected(true);
      //this.roles = this.tokenStorage.getUser().roles;
    } else {
      this.createLoginForm();
      this.workDayService.setIsConnected(false);
    }
  }

  onSubmit() {
    this.authService.login(this.loginForm).subscribe(
      data => {
        console.log("ce vine", data);
        console.log("username", data.username);
        this.tokenStorage.saveToken(data.accessToken);
        //this.employeeService.setStoredEmployee(data.username);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.workDayService.setIsConnected(true);
        console.log("Am navigat home");
        this.router.navigate(['/personalData']);
        //this.roles = this.tokenStorage.getUser().roles;
        // this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  functie() {
    this.authService.register(["copilescumic", "copilescu@gmail.com", "copilescumic"]).subscribe(
      data => {
        // this.tokenStorage.saveToken(data.accessToken);
        // this.tokenStorage.saveUser(data);
        //
        // this.isLoginFailed = false;
        //   this.isConnected.emit(true);
        //this.roles = this.tokenStorage.getUser().roles;
        // this.reloadPage();
        console.log("s-a apeelat mizeria");
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }

  private createLoginForm() {
    this.loginForm = this.formBuilder.group({
      'username': [this.username, [Validators.required, Validators.maxLength(30)]],
      'password': [this.password, [Validators.required, Validators.minLength(8), Validators.maxLength(30)]]
    });
  }
}
