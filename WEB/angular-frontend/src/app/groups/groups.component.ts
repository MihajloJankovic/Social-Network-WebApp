import {Component, Input} from '@angular/core';
import {AuthServiceService} from "../Services/auth.service.service";
import {ActivatedRoute, Router} from "@angular/router";
import {GroupService} from "../Services/group.service";
import {FormControl, FormGroup} from "@angular/forms";

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
  async ngOnInit() {


    this.groupService.getAll().subscribe((data) => {
      this.groups= data;

      this.b=1;
    });

  }





}
