package rs.ac.uns.ftn.svtvezbe07.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Comment;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;
import rs.ac.uns.ftn.svtvezbe07.repository.CommentRepository;

import java.util.Optional;

@Service

@Primary
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Optional<Comment> GetOneById(Long id) {

        return  this.commentRepository.findById(id);


    }
    public void save(Comment dt) {
        this.commentRepository.save(dt);
    }
}
