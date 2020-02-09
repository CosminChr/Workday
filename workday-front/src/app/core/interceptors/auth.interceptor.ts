import {HTTP_INTERCEPTORS, HttpErrorResponse, HttpEvent, HttpResponse} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Router } from '@angular/router';
import { TokenStorageService } from '../services/security/token-storage.service';
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService,
              private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let authReq = req;
    const token = this.token.getToken();
    if (token != null) {
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    } else {

    }
    return next.handle(authReq).pipe(catchError(err => {
      if (err.status === 401) {
        this.router.navigate(['/login']);
      }
      const error = err.error.message || err.statusText;
      return throwError(error);
    }));
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];

