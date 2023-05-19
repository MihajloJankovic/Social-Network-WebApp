package rs.ac.uns.ftn.svtvezbe07.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.dto.PostDTO;
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

    @GetMapping("/All")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<postDTO> user(Principal user) {

        User pera = this.userService.findByUsername(user.getName());
        return postservice.getAll(pera.getId());

    }




}
