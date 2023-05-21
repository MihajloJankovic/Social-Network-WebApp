import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AuthServiceService} from "../Services/auth.service.service";
import {ActivatedRoute, Router} from "@angular/router";
import {GroupService} from "../Services/group.service";
import {PostService} from "../Services/post.service";

@Component({
  selector: 'app-group-create',
  templateUrl: './group-create.component.html',
  styleUrls: ['./group-create.component.css']
})
export class GroupCreateComponent {
  forma = new FormGroup({
    name: new FormControl(''),
    description : new FormControl(''),
  });

  submitted = false;

  /**
   * Notification message from received
   * form request or router
   */




  private returnUrl: any;
  public a: any;

  constructor(
    private authService: AuthServiceService,
    private router: Router,
    private route: ActivatedRoute,
    private groupService: GroupService,

  ) {
    if(this.authService.isAuthenticated())
    {

    }
    else {
      let returnUrl : String;
      returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
      this.router.navigate([returnUrl]);
    }

  }

  ngOnInit() {

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

  }


  back()
  {
    let returnUrl : String;
    returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.router.navigate([returnUrl]);
  }
  onSubmit() {
    /**
     * Innocent until proven guilty
     */

    this.submitted = true;
    console.warn('Your order has been submitted', this.forma.value);
    this.groupService.create(this.forma.value)


  }

}
