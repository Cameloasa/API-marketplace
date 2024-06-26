package se.lexicon.g49marketplace.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.g49marketplace.domain.dto.UserDTOForm;
import se.lexicon.g49marketplace.domain.dto.UserDTOView;
import se.lexicon.g49marketplace.service.UserService;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserDTOView> doRegisterUser( @RequestBody @Valid UserDTOForm dtoForm) {
        System.out.println(" Register DTO Form: " + dtoForm);
        UserDTOView responseBody = userService.registerUser(dtoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserDTOView> doAuthenticateUser(@RequestBody @Valid UserDTOForm dtoForm) {
        System.out.println("Authenticate DTO Form: " + dtoForm);
        UserDTOView responseBody = userService.authenticateUser(dtoForm);
        return ResponseEntity.status(HttpStatus.FOUND).body(responseBody);
    }
}
