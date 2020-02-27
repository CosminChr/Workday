import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(credentials): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username: credentials.value.username,
      password: credentials.value.password
    }, httpOptions);
  }

  register(user): Observable<any> {
    console.log(user);
    return this.http.post(AUTH_API + 'signup', {
      username: user[0],
      email: user[1],
      password: user[2]
    }, httpOptions);
  }
}
