package rs.ac.uns.ftn.svtvezbe07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;
import rs.ac.uns.ftn.svtvezbe07.model.entity.post;

import java.util.List;
import java.util.Optional;

public interface PostRepository  extends JpaRepository<post, Long> {
    List<post> findAllByUserAndDeleted(Long User,boolean Deleted);
    post findFirstById(Long Id);



}
