package se.lexicon.g49marketplace.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g49marketplace.domain.dto.UserDTOForm;
import se.lexicon.g49marketplace.domain.dto.UserDTOView;
import se.lexicon.g49marketplace.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
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

    @GetMapping
    public ResponseEntity<UserDTOView> doFindUserByEmail(
            @RequestParam
            @NotEmpty
            @NotNull
            @Email
            String email) {
        System.out.println(">>>>>>> Find User By Email has been executed");
        System.out.println("Find User By Email: " + email);
        UserDTOView responseBody = userService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
}
