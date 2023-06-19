import {Component, Input} from '@angular/core';
import {AuthServiceService} from "../Services/auth.service.service";
import {ActivatedRoute, Router} from "@angular/router";
import {GroupService} from "../Services/group.service";
import {FormControl, FormGroup} from "@angular/forms";
import {UserServiceService} from "../user-service.service";

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent {
 groups:any;
 b = 0;
  constructor(
    private authService: AuthServiceService,
    private router: Router,
    private userService: UserServiceService,
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

  getElementId(event:any){

    // Get the source element
    let element = event.target || event.srcElement || event.currentTarget;
    // Get the id of the source element
    let elementId = element.id;
    if(element.innerText == "delete")
    {
      this.groupService.delete(elementId);
    }
    if(element.innerText == "edit")
    {
      let returnUrl : String;
      returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
      this.router.navigate([returnUrl + '/group',elementId]);
    }


  }
  forma :any;
  ba = 0;
  async ngOnInit() {


    this.groupService.getAll().subscribe((data) => {
      this.groups= data;

      this.b=1;
    });






    this.userService.getOne().subscribe((data) => {
      this.user  = data;
      this.forma = new FormGroup({
        id: new FormControl(this.user.id),
        name: new FormControl(this.user.displayName),
        desc: new FormControl(this.user.description),

      });
      this.ba=1;
    });

  }
  id :any;
  user:any;


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
    returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.router.navigate([returnUrl+"/HomePage"]);
  }
  onSubmit() {
    /**
     * Innocent until proven guilty
     */
    if(this.forma.value.name.length>2 && this.forma.value.desc.length>2 ) {
      this.submitted = true;
      console.warn('Changged', this.forma.value);
      this.userService.changeDisplayName(this.forma.value);
    }

  }





}
