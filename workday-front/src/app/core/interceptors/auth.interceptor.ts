import {
  HTTP_INTERCEPTORS,
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest, HttpResponse
} from '@angular/common/http';
import {Injectable} from '@angular/core';
import 'rxjs/add/operator/do';

import {TokenStorageService} from '../services/security/token-storage.service';
import {Router} from "@angular/router";
import {WorkdayService} from "../../workday.service";
import {NotificationService} from "../../shared/services/notification/notification.service";
import {AuthService} from "../services/security/auth.service";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private tokenStorageService: TokenStorageService,
              private router: Router,
              private workdayService: WorkdayService,
              private notificationService: NotificationService,
              private authService: AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let authReq = req;
    const token = this.tokenStorageService.getToken();
    if (authReq.url.match('multipart')) {
      const multipartReq = req.clone({
        headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token)
      });
      return next.handle(multipartReq);
    }

    if (token != null) {
      authReq = req.clone({
        headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token)
          .set('Content-Type', 'application/json')
      });
    }
    return next.handle(authReq).do((event: HttpEvent<any>) => {
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401 && localStorage.getItem('auth-token')) {
          this.authService.logout();
          this.router.navigate(['/login']);
          this.notificationService.showNotification('top','center', 'danger', 'Sesiunea a expirat. Te rugăm să te conectezi din nou.');
        }
      }
    });
  }
}

export const authInterceptorProvider = [
  {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
];
