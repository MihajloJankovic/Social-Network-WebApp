package rs.ac.uns.ftn.svtvezbe07.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.dto.PostDTO;
import rs.ac.uns.ftn.svtvezbe07.model.dto.PostSaveDTO;
import rs.ac.uns.ftn.svtvezbe07.model.dto.postDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;
import rs.ac.uns.ftn.svtvezbe07.model.entity.post;
import rs.ac.uns.ftn.svtvezbe07.security.TokenUtils;
import rs.ac.uns.ftn.svtvezbe07.service.UserService;
import rs.ac.uns.ftn.svtvezbe07.service.implementation.PostServieimpl;

import java.security.Principal;
import java.util.List;



@CrossOrigin
@RestController
@RequestMapping("api/post")
public class PostController  {

    @Autowired
    PostServieimpl postservice;
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

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public post user(Principal user, @RequestBody @Validated PostDTO dto) {

        User pera = this.userService.findByUsername(user.getName());

        post b = this.postservice.createGroup(dto.getTekst(),pera.getId());
        pera.getPosts().add(b);
        userService.Save(pera);
        return b;
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public HttpStatus user1(Principal user, @RequestBody @Validated  Long id) {
       // Long id = Long.valueOf(idd);
        User pera = this.userService.findByUsername(user.getName());

        if( this.postservice.getOne(id).getUser() == pera.getId())
        {
            this.postservice.delete(id);
            return HttpStatus.ACCEPTED;
        }
        else return HttpStatus.NOT_ACCEPTABLE;
    }
    @GetMapping("/All")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<postDTO> user(Principal user) {

        User pera = this.userService.findByUsername(user.getName());
        return postservice.getAll(pera.getId());

    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public HttpStatus userf(Principal user, @RequestBody @Validated PostSaveDTO dto ) {

        User pera = this.userService.findByUsername(user.getName());
        post peraa = postservice.getOne(dto.getId());
       if(peraa.getUser() == pera.getId())
       {
           peraa.setText(dto.getText());
           postservice.save(peraa);
           return HttpStatus.ACCEPTED;
       }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    @PostMapping("/one")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public post userf(Principal user, @RequestBody @Validated Long id ) {

        User pera = this.userService.findByUsername(user.getName());
        post peraa = postservice.getOne(id);
        if( peraa.getUser() == pera.getId())
        {

            return this.postservice.getOne(id);
        }
        else return null;
    }
}
