package rs.ac.uns.ftn.svtvezbe07.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.svtvezbe07.model.dto.GroupDTO;
import rs.ac.uns.ftn.svtvezbe07.model.dto.postDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Group;
import rs.ac.uns.ftn.svtvezbe07.model.entity.post;
import rs.ac.uns.ftn.svtvezbe07.repository.groupRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

@Primary
public class GroupServiceimpl {
    @Autowired
    private groupRepository GroupRepository;
    public List<Group> findAll() {

      return  this.GroupRepository.findAllByDeleted(false);


    }
    public void save(Group dt) {
        this.GroupRepository.save(dt);
    }
    public void delete( Long Id) {
        Group grupa =  this.GroupRepository.findFirstById(Id);
        grupa.setDeleted(true);
        this.GroupRepository.save(grupa);

    }
    public Group getOne(Long Id) {
        return this.GroupRepository.findFirstById(Id);


    }
    public Group createGroup(GroupDTO dto,long a) {

        Optional<Group> group = GroupRepository.findFirstByNameAndDeleted(dto.getName(),false);

        if(group.isPresent()){
            return null;
        }

        Group groupa = new Group();
        groupa.setName(dto.getName());
        groupa.setDate(LocalDateTime.now());
        groupa.setDescription(dto.getDescription());
        groupa.setGroupAdmin(a);
        groupa.setDeleted(false);
        groupa = GroupRepository.save(groupa);


        return groupa;
    }
}
