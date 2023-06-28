package rs.ac.uns.ftn.svtvezbe07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Reaction;
import rs.ac.uns.ftn.svtvezbe07.model.entity.post;

public interface ReactionRepository  extends JpaRepository<Reaction, Long> {
}
