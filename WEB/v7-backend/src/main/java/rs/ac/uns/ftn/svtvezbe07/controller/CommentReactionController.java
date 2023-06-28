package rs.ac.uns.ftn.svtvezbe07.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.dto.reactionDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.*;
import rs.ac.uns.ftn.svtvezbe07.security.TokenUtils;
import rs.ac.uns.ftn.svtvezbe07.service.UserService;
import rs.ac.uns.ftn.svtvezbe07.service.implementation.CommentService;
import rs.ac.uns.ftn.svtvezbe07.service.implementation.GroupServiceimpl;
import rs.ac.uns.ftn.svtvezbe07.service.implementation.PostServieimpl;
import rs.ac.uns.ftn.svtvezbe07.service.implementation.ReactionService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/comment")
public class CommentReactionController {
    @Autowired
    CommentService commentService;
    @Autowired
    PostServieimpl postServieimpl;
    @Autowired
    ReactionService reactionService;

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping("/addReaction")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public HttpStatus user1(Principal user, @RequestBody @Validated reactionDTO dto){
        User pera = this.userService.findByUsername(user.getName());
        post p = this.postServieimpl.getOne(dto.getId());
        Reaction r = new Reaction();
        r.setPost(p.getId());
        r.setUser(pera.getId());
        r.setDate(LocalDate.now());

        if(dto.getType()==1){
           r.setType(Reactions.LIKE);
        }
        if(dto.getType()==2){
            r.setType(Reactions.DISLIKE);
        }
        if(dto.getType()==3){
            r.setType(Reactions.HEART);
        }
        reactionService.save(r);
        p.getReactions().add(r);
        postServieimpl.save(p);
        return HttpStatus.CREATED;
    }
}