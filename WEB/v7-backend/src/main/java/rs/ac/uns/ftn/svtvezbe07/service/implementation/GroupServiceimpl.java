package rs.ac.uns.ftn.svtvezbe07.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.dto.GroupDTO;
import rs.ac.uns.ftn.svtvezbe07.model.dto.UserDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Roles;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;
import rs.ac.uns.ftn.svtvezbe07.repository.UserRepository;
import rs.ac.uns.ftn.svtvezbe07.repository.groupRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

@Primary
public class GroupServiceimpl {
    @Autowired
    private groupRepository GroupRepository;
    public List<Group> findAll() {
        return this.GroupRepository.findAll();
    }
    public Group createGroup(GroupDTO dto,long a) {

        Optional<Group> group = GroupRepository.findFirstByName(dto.getName());

        if(group.isPresent()){
            return null;
        }

        Group groupa = new Group();
        groupa.setName(dto.getName());
        groupa.setDate(LocalDateTime.now());
        groupa.setDescription(dto.getDescription());
        groupa.setGroupAdmin(a);
        groupa = GroupRepository.save(groupa);


        return groupa;
    }
}
