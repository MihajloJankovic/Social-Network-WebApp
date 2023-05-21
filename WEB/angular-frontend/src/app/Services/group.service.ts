import { Injectable } from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {ApiService} from "../api-service.service";
import {UserServiceService} from "../user-service.service";
import {ConfigServiceService} from "./config-service.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class GroupService {

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

  create(user:any) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    // const body = `username=${user.username}&password=${user.password}`;
    const body = {
      'name': user.name,
      'description': user.description,


    };
    return this.apiService.post(this.config._groupcreate_url, JSON.stringify(body), loginHeaders)
      .subscribe((res) => {
        if(res.body == "NOT_ACCEPTABLE" || res.name == "HttpErrorResponse")
        {
          alert("wrong Details")
        }else {
          console.log('Creation success');
          let returnUrl : String;
          returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
          this.router.navigate([returnUrl + "/HomePage"]);
        }
      });
  }
  save(post:any) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    // const body = `username=${user.username}&password=${user.password}`;
    const body = {
      'id': post.id,
      'name': post.name,
      'description': post.Description,

    };
    return this.apiService.post(this.config._groupsave_url, JSON.stringify(body), loginHeaders)
      .subscribe((res) => {
        if(res.body == "NOT_ACCEPTABLE" || res.name == "HttpErrorResponse")
        {
          alert("Error")
        }else {
          alert("Save success");
          let returnUrl : String;
          returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
          this.router.navigate([returnUrl + "/HomePage"]);
        }
      });
  }
  public g : any;
  getAll() {



    return this.apiService.get(this.config._groupall_url);

  }
  delete(id:any) {

    // const body = `username=${user.username}&password=${user.password}`;
    const body = id;
    return this.apiService.delete(this.config._groupdelete_url, JSON.stringify(body))
      .subscribe((res) => {
        if(res.body == "NOT_ACCEPTABLE" || res.name == "HttpErrorResponse")
        {
          alert("Error")
        }else {
          alert("Delete success");
          window.location.reload();
        }
      });
  }
  getOne(a:any) {



    // const body = `username=${user.username}&password=${user.password}`;
    const body =a;

    return this.apiService.post(this.config._groupone_url,body);

  }
}
