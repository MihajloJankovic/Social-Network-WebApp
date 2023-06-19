import {Injectable} from '@angular/core';
import {ApiService} from './api-service.service';
import {ConfigServiceService} from './Services/config-service.service';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  currentUser :any;

  constructor(
    private apiService: ApiService,
    private config: ConfigServiceService
  ) {
  }

  getOne() {


    return this.apiService.get(this.config._userone_url);

  }

  changeDisplayName(post:any) {

    // const body = `username=${user.username}&password=${user.password}`;
    const body = {

      'name': post.name,
      'desc': post.desc,

    };
    return this.apiService.post(this.config._userDispayNameSave_url, body)
      .subscribe((res) => {
        if(res.body == "NOT_ACCEPTABLE" || res.name == "HttpErrorResponse")
        {
          alert("Error")
        }else {
          alert("Save success");
          window.location.reload();
        }
      });
  }

}
