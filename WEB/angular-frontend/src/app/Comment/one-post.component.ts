import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {PostService} from "../Services/post.service";
import {FormControl, FormGroup} from "@angular/forms";
import {AuthServiceService} from "../Services/auth.service.service";
import {CommentServiceService} from "../Services/comment-service.service";
@Component({
  selector: 'app-one-post',
  templateUrl: './one-post.component.html',
  styleUrls: ['./one-post.component.css']
})
export class CommentComponent {

constructor(private _Activatedroute:ActivatedRoute,private postService : PostService,private commentService : CommentServiceService, private router: Router,  private authService: AuthServiceService,) {


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
   ngOnInit() {
     let id = this._Activatedroute.snapshot.paramMap.get("id");
     this.forma = new FormGroup({
       postid: new FormControl(id),
       comment: new FormControl(''),
     });

  }
  submitted:any;

  onSubmit() {
    /**
     * Innocent until proven guilty
     */

    this.submitted = true;
    console.warn('Your order has been submitted', this.forma.value);
    this.commentService.create(this.forma.value)

    let returnUrl : String;
    returnUrl = this._Activatedroute.snapshot.queryParams['returnUrl'] || '/';
    this.router.navigate([returnUrl + "/HomePage"]);


  }
}
