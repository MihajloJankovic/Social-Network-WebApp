package rs.ac.uns.ftn.svtvezbe07.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.svtvezbe07.model.dto.GroupDTO;
import rs.ac.uns.ftn.svtvezbe07.model.dto.GroupReciveDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;
import rs.ac.uns.ftn.svtvezbe07.security.TokenUtils;
import rs.ac.uns.ftn.svtvezbe07.service.UserService;
import rs.ac.uns.ftn.svtvezbe07.service.implementation.GroupServiceimpl;

import java.security.Principal;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("api/group")
public class GroupController  {

    @Autowired
    GroupServiceimpl groupservice;
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

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Group> user1(Principal user) {
        return this.groupservice.findAll();
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public HttpStatus user1(Principal user, @RequestBody @Validated  Long id) {
        User pera = this.userService.findByUsername(user.getName());

        if( this.groupservice.getOne(id).getGroupAdmin() == pera.getId())
        {
            this.groupservice.delete(id);
            return HttpStatus.ACCEPTED;
        }
        else return HttpStatus.NOT_ACCEPTABLE;
    }
    @PostMapping("/one")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Group usesr1(Principal user, @RequestBody @Validated  Long id) {
        User pera = this.userService.findByUsername(user.getName());

        if( this.groupservice.getOne(id).getGroupAdmin() == pera.getId())
        {
            return groupservice.getOne(id);

        }
        else return null;
    }
    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Group usesr1(Principal user, @RequestBody @Validated GroupReciveDTO dto) {
        User pera = this.userService.findByUsername(user.getName());

        if( this.groupservice.getOne(dto.getId()).getGroupAdmin() == pera.getId())
        {
            Group perab =  groupservice.getOne(dto.getId());
            perab.setName(dto.getName());
            perab.setDescription(dto.getDescription());
            groupservice.save(perab);

        }
        else return null;
        return null;
    }
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Group user(Principal user,@RequestBody @Validated GroupDTO dto) {

        User pera = this.userService.findByUsername(user.getName());
        return this.groupservice.createGroup(dto,pera.getId());
    }





}
