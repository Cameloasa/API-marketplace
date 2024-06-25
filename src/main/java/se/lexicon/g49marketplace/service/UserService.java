package se.lexicon.g49marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g49marketplace.domain.entity.User;
import se.lexicon.g49marketplace.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(String email) {
        return userRepository.findByEmail(email);
    }

}
