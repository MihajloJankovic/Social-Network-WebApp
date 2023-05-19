package rs.ac.uns.ftn.svtvezbe07.service;

import rs.ac.uns.ftn.svtvezbe07.model.dto.UserDTO;
import rs.ac.uns.ftn.svtvezbe07.model.entity.User;

import java.util.List;

public interface UserService {
    User Save(User n);
    User findByUsername(String username);
    User findOne(Long a);
    User createUser(UserDTO userDTO);

    List<User> findAll();
    void ChangePassword(String username,String password);
}
