import {Component, Input} from '@angular/core';
import {PostService} from "../Services/post.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent {
  constructor(private postService : PostService, private router: Router,
              private route: ActivatedRoute,) {

  }
  @Input() posts:any;

  getElementId(event:any){

    // Get the source element
    let element = event.target || event.srcElement || event.currentTarget;
    // Get the id of the source element
    let elementId = element.id;
    if(element.innerText == "delete")
    {
      this.postService.delete(elementId);
    }
    if(element.innerText == "edit")
    {
      let returnUrl : String;
      returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
      this.router.navigate([returnUrl + '/post',elementId]);
    }


  }
}
