package rs.ac.uns.ftn.svtvezbe07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface groupRepository extends JpaRepository<Group, Long> {
    Group findFirstById(Long Id);
    List<Group> findAllByDeleted(Boolean Deleted);
    Optional<Group> findFirstByNameAndDeleted(String Name,Boolean Deleted);




}


