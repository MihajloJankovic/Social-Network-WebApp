import {Component, Input} from '@angular/core';
import {PostService} from "../Services/post.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CommentServiceService} from "../Services/comment-service.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-posts-all',
  templateUrl: './posts-all.component.html',
  styleUrls: ['./posts-all.component.css']
})
export class PostsAllComponent {
  constructor(private postService : PostService,private commentService : CommentServiceService, private router: Router,
              private route: ActivatedRoute,) {

  }
  @Input() posts:any;

  getElementId(event:any){

    // Get the source element
    let element = event.target || event.srcElement || event.currentTarget;
    // Get the id of the source element
    let elementId = element.id;

    if(element.innerText == "Like")
    {
      this.commentService.ReactionNew(elementId,1);
    }
    if(element.innerText == "Dislike")
    {
      this.commentService.ReactionNew(elementId,2);
    }
    if(element.innerText == "Heart")
    {
      this.commentService.ReactionNew(elementId,3);
    }
    if(element.innerText == "Like Comment")
    {
      this.commentService.ReactionNewCOmment(elementId,1);
    }
    if(element.innerText == "Dislike Comment")
    {
      this.commentService.ReactionNewCOmment(elementId,2);
    }
    if(element.innerText == "Heart Comment")
    {
      this.commentService.ReactionNewCOmment(elementId,3);
    }


  }

}
