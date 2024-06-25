package se.lexicon.g49marketplace.service;

import se.lexicon.g49marketplace.domain.dto.UserDTOForm;
import se.lexicon.g49marketplace.domain.dto.UserDTOView;

import java.util.List;

public interface UserService {

    //register(email, password, username)
    UserDTOView register(UserDTOForm dtoForm);
    //findByEmail
    UserDTOView findByEmail(String email);
    //findAll
    List<UserDTOView> findAll();
    // update
    UserDTOView update(UserDTOForm dtoForm);
    //delete
    void delete(String email);
}
