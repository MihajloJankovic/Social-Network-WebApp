import { Injectable } from '@angular/core';
import {HttpHeaders, HttpStatusCode} from '@angular/common/http';

import { ConfigServiceService} from './config-service.service';
import { catchError, map } from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from "../api-service.service";
import {UserServiceService} from "../user-service.service";
import { JwtHelperService } from '@auth0/angular-jwt';
@Injectable({providedIn: 'root'})
export class AuthServiceService {
  get access_token(): any {
    return this._access_token;
  }

  set access_token(value: any) {
    this._access_token = value;
  }

  constructor(
    public jwtHelper: JwtHelperService,
    private apiService: ApiService,
    private userService: UserServiceService,
    private config: ConfigServiceService,
    private router: Router,
    private route: ActivatedRoute,

  ) {

  }
  public isAuthenticated(): boolean {
    const token = localStorage.getItem('jwt');
    // Check whether the token is expired and return
    // true or false
    if(this.jwtHelper.isTokenExpired(token))
    {
      localStorage.clear();
      return false;
    }
    return true;
  }

  private _access_token = null;
  login(user:any) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    // const body = `username=${user.username}&password=${user.password}`;
    const body = {
      'username': user.username,
      'password': user.password
    };
    return this.apiService.post(this.config.login_url, JSON.stringify(body), loginHeaders)
      .subscribe((res) => {
        console.log('Login success');
        this._access_token = res.body.accessToken;
        localStorage.setItem("jwt", res.body.accessToken)
        let returnUrl : String;
        returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
        this.router.navigate([returnUrl + "/HomePage"]);
      });
  }
  changePassword(user:any) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    // const body = `username=${user.username}&password=${user.password}`;
    const body = {
      'newPassword': user.NewPassword,
      'oldPassword': user.OldPassword,
      'oldPassword2': user.OldPassword2
    };
    return this.apiService.post(this.config._passchange_url, JSON.stringify(body), loginHeaders)
      .subscribe((res) => {
       if(res.body == "NOT_ACCEPTABLE")
       {
         alert("wrong Password")
       }else {
         console.log('Change success');
         let returnUrl : String;
         returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
         this.router.navigate([returnUrl + "/HomePage"]);
       }
      });
  }
  signup(user:any) {
    const signupHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    const body = {
      'username': user.username,
      'password': user.password,
      'email': user.email,
      'firstname': user.fname,
      'lastname': user.lname,
    };
    return this.apiService.post(this.config._signup_url, JSON.stringify(body), signupHeaders)
      .subscribe((res) => {
        if(res.body == "NOT_ACCEPTABLE" || res.name == "HttpErrorResponse")
        {
          alert("Somthing is wrong")
        }else {
          console.log('Sign up success');
          let returnUrl : String;
          returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
          this.router.navigate([returnUrl + "/login"]);
        }
      });
  }

  logout() {
    this.userService.currentUser = null;
    this._access_token = null;
    this.router.navigate(['/login']);
  }

  tokenIsPresent() {
    return this._access_token != undefined && this._access_token != null;
  }

  getToken() {
    return this._access_token;
  }

}
