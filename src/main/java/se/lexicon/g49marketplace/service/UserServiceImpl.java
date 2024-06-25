package se.lexicon.g49marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g49marketplace.converter.UserConverter;
import se.lexicon.g49marketplace.domain.dto.UserDTOForm;
import se.lexicon.g49marketplace.domain.dto.UserDTOView;
import se.lexicon.g49marketplace.domain.entity.User;
import se.lexicon.g49marketplace.exception.DataDuplicateException;
import se.lexicon.g49marketplace.exception.DataNotFoundException;
import se.lexicon.g49marketplace.repository.UserRepository;
import se.lexicon.g49marketplace.util.CustomPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private CustomPasswordEncoder customPasswordEncoder;
    private UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CustomPasswordEncoder customPasswordEncoder, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.customPasswordEncoder = customPasswordEncoder;
        this.userConverter = userConverter;
    }

    @Override
    @Transactional
    public UserDTOView register(UserDTOForm dtoForm) {
        //check for params
        if(dtoForm == null) throw new IllegalArgumentException("dtoForm cannot be null");
        //check if the email exists in the database
        boolean exists = userRepository.existsByEmail(dtoForm.getEmail());
        if(exists) {
            throw new DataDuplicateException("Email already exists.");
        }
        //Convert user DTO form to User entity and hash the password
        User user = User.builder()
                .email(dtoForm.getEmail())
                .password(customPasswordEncoder.encode(dtoForm.getPassword()))
                .username(dtoForm.getUsername())
                .phoneNumber(dtoForm.getPhoneNumber())
                .build();
        //save the user to the database
        User savedUser = userRepository.save(user);
        // return the result
        return UserDTOView.builder()
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .phoneNumber(savedUser.getPhoneNumber())
                .build();
    }

    @Override
    public UserDTOView findByEmail(String email) {
        //check if the user's email exist in the database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("Email not found"));
        //return the result
        return UserDTOView.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @Override
    public List<UserDTOView> findAll() {
        //fetch the user from database
        List<User> users = userRepository.findAll();
        //return a list of person
        return users.stream().map(userConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTOView update(UserDTOForm dtoForm) {
        //Check the param
        if(dtoForm == null) throw new IllegalArgumentException("dtoForm is not accepted");
        //find the existing user
        User existingUser = userRepository.findByEmail(dtoForm.getEmail())
                .orElseThrow(() -> new DataNotFoundException("Email not found"));
        // Update the user's details
        existingUser.setEmail(dtoForm.getEmail());
        // Update associated user if email is provided
        existingUser.setUsername(dtoForm.getUsername());
        existingUser.setPhoneNumber(dtoForm.getPhoneNumber());
        existingUser.setPassword(customPasswordEncoder.encode(dtoForm.getPassword()));
        //Save the updated user
        User savedUser = userRepository.save(existingUser);
        //return user
        return userConverter.toDTO(savedUser);
    }

    @Override
    @Transactional
    public void delete(String email) {
        // Check if the user exists
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("Email not found"));

        // Delete the user
        userRepository.delete(user);

    }
}
