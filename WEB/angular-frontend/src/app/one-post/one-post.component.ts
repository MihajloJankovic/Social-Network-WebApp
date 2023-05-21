import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {PostService} from "../Services/post.service";
import {FormControl, FormGroup} from "@angular/forms";
import {AuthServiceService} from "../Services/auth.service.service";
@Component({
  selector: 'app-one-post',
  templateUrl: './one-post.component.html',
  styleUrls: ['./one-post.component.css']
})
export class OnePostComponent {

constructor(private _Activatedroute:ActivatedRoute,private postService : PostService, private router: Router,  private authService: AuthServiceService,) {


  if(this.authService.isAuthenticated())
  {

  }
  else {
    let returnUrl : String;
    returnUrl = this._Activatedroute.snapshot.queryParams['returnUrl'] || '/';
    this.router.navigate([returnUrl]);
  }
}
forma :any;
  async ngOnInit() {

    this.id = this._Activatedroute.snapshot.paramMap.get("id");
     this.postService.getOne(this.id).subscribe((data) => {
       this.post  = data.body;
       this.forma = new FormGroup({
         id: new FormControl(this.post.id),
         text: new FormControl(this.post.text),

       });
       this.b=1;
    });

  }
  id :any;
  post:any;
  b= 0;

  submitted = false;

  /**
   * Notification message from received
   * form request or router
   */




  private returnUrl: any;
  public a: any;
  back()
  {
    let returnUrl : String;
    returnUrl = this._Activatedroute.snapshot.queryParams['returnUrl'] || '/';
    this.router.navigate([returnUrl+"/HomePage"]);
  }
  onSubmit() {
    /**
     * Innocent until proven guilty
     */

    this.submitted = true;
    console.warn('Your order has been submitted', this.forma.value);
   this.postService.save(this.forma.value);


  }
}
