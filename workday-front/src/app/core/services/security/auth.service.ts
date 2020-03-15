import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {WorkdayService} from "../../../workday.service";

const AUTH_API = 'auth/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
              private router: Router,
              private workdayService: WorkdayService) {
  }

  login(credentials): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username: credentials.value.username,
      password: credentials.value.password
    }, httpOptions);
  }

  register(user): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username: user[0],
      email: user[1],
      password: user[2]
    }, httpOptions);
  }

  logout() {
    localStorage.clear();
    this.workdayService.setIsConnected(false);
    this.router.navigate(['/login']);
  }
}
