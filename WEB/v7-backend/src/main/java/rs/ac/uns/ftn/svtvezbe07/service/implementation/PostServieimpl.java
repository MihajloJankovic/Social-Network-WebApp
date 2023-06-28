package rs.ac.uns.ftn.svtvezbe07.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.dto.postDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.*;
import rs.ac.uns.ftn.svtvezbe07.repository.PostRepository;
import rs.ac.uns.ftn.svtvezbe07.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service

@Primary
public class PostServieimpl {
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;
    public post createGroup(String  dto, long a) {


        post post1 = new post();
        post1.setText(dto);
        post1.setDate(LocalDateTime.now());
        post1.setUser(a);
        post1.setDeleted(false);
       post1 = postRepository.save(post1);
        return post1;
    }
    public void delete( Long Id) {
        post grupa =  this.postRepository.findFirstById(Id);
        grupa.setDeleted(true);
        this.postRepository.save(grupa);

    }
    public post getOne(Long Id) {
        return this.postRepository.findFirstById(Id);


    }
    public post save(post pp) {
        return this.postRepository.save(pp);


    }
    public List<postDTO> getAll(long a) {

        List<postDTO> dtos= new ArrayList<postDTO>();
        List<post> posts = postRepository.findAllByUserAndDeleted(a,false);
        for ( post aa: posts
             ) {
            postDTO pera = new postDTO();

            if (userService.findOne(aa.getUser()).getDisplayName() == null || userService.findOne(aa.getUser()).getDisplayName().length() == 0)
                pera.setUser(userService.findOne(aa.getUser()).getFirstname());
            else
                pera.setUser(userService.findOne(aa.getUser()).getDisplayName());
            pera.setTekst(aa.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            String formatDateTime = aa.getDate().format(formatter);
            pera.setDat(formatDateTime);
            pera.setId(aa.getId());
            dtos.add(pera);
        }
        return dtos;
    }
    public List<postDTO> getAllAll() {

        List<postDTO> dtos= new ArrayList<postDTO>();
        List<post> posts = postRepository.findAllByDeleted(false);
        for ( post aa: posts
        ) {
            postDTO pera = new postDTO();

            if (userService.findOne(aa.getUser()).getDisplayName() == null || userService.findOne(aa.getUser()).getDisplayName().length() == 0)
                pera.setUser(userService.findOne(aa.getUser()).getFirstname());
            else
                pera.setUser(userService.findOne(aa.getUser()).getDisplayName());
            pera.setTekst(aa.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            String formatDateTime = aa.getDate().format(formatter);
            pera.setDat(formatDateTime);
            pera.setId(aa.getId());
            for (Reaction b: aa.getReactions())
            {
                if(b.getType()== Reactions.LIKE) pera.setLike(pera.getLike()+1);
                if(b.getType()== Reactions.DISLIKE) pera.setDislike(pera.getDislike()+1);
                if(b.getType()== Reactions.HEART) pera.setHearth(pera.getHearth()+1);

            }
            pera.setComments( aa.getComments());
            dtos.add(pera);
        }
        return dtos;
    }
}
