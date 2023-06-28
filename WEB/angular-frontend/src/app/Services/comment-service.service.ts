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
export class CommentServiceService {

  constructor(  public jwtHelper: JwtHelperService,
                private apiService: ApiService,
                private userService: UserServiceService,
                private config: ConfigServiceService,
                private router: Router,
                private route: ActivatedRoute,) { }

  ReactionNew(id:any,type:any) {

    // const body = `username=${user.username}&password=${user.password}`;



      const loginHeaders = new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      });
      const body = {
        'id': id,
        'type': type,

      };
    return this.apiService.post(this.config._addReaction_url, JSON.stringify(body))
      .subscribe((res) => {
        if(res.body == "NOT_ACCEPTABLE" || res.name == "HttpErrorResponse")
        {
          alert("Error")
        }else {
          window.location.reload();
        }
      } ,
        (error) => {
          alert("Allready Did That !");


        }

    );

  }
}
