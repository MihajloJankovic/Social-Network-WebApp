package rs.ac.uns.ftn.svtvezbe07.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Comment;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Reaction;
import rs.ac.uns.ftn.svtvezbe07.repository.ReactionRepository;

import java.util.Optional;

@Service

@Primary
public class ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;

    public Optional<Reaction> GetOneById(Long id) {

        return  this.reactionRepository.findById(id);


    }
    public void save(Reaction dt) {
        this.reactionRepository.save(dt);
    }
}
