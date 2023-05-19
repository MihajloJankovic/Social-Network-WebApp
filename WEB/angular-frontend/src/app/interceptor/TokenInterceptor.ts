import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpInterceptor,
  HttpEvent
} from '@angular/common/http';


import { Observable, } from 'rxjs/internal/Observable';
import {AuthServiceService} from "../Services/auth.service.service";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(public auth: AuthServiceService, public jwtHelper: JwtHelperService) { }
  public isAuthenticated(): boolean {
    const token = localStorage.getItem('jwt');
    // Check whether the token is expired and return
    // true or false
    return !this.jwtHelper.isTokenExpired(token);
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {


  if(this.isAuthenticated())
  {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${localStorage.getItem("jwt")}`
        }
      });
    }
    return next.handle(request);
  }
}
