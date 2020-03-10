import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../core/services/security/auth.service';
import {TokenStorageService} from '../../core/services/security/token-storage.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {EmployeeService} from "../../shared/services/employee/employee.service";
import {WorkdayService} from "../../workday.service";
import {HttpHeaders} from "@angular/common/http";

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
              private tokenStorageService: TokenStorageService,
              private employeeService: EmployeeService,
              private workDayService: WorkdayService,
              private formBuilder: FormBuilder,
              private router: Router) {
  }

  ngOnInit() {
    if (this.tokenStorageService.getToken()) {
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
        this.tokenStorageService.saveToken(data.accessToken);
        this.tokenStorageService.saveUser(data);
        //this.roles = this.tokenStorage.getUser().roles;
        this.isLoginFailed = false;
        this.workDayService.setIsConnected(true);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }, () => {
        this.router.navigate(['/profile/personalData']);
      });
  }

  private createLoginForm() {
    this.loginForm = this.formBuilder.group({
      'username': [this.username, [Validators.required, Validators.maxLength(30)]],
      'password': [this.password, [Validators.required, Validators.minLength(8), Validators.maxLength(30)]]
    });
  }
}
