package rs.ac.uns.ftn.svtvezbe07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Comment;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
