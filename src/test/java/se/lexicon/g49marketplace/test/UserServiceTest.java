package se.lexicon.g49marketplace.test;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.lexicon.g49marketplace.domain.entity.User;
import se.lexicon.g49marketplace.exception.GlobalExceptionHandler;
import se.lexicon.g49marketplace.repository.UserRepository;
import se.lexicon.g49marketplace.service.UserService;

import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User registeredUser = userService.registerUser(user);
        assertEquals("test@example.com", registeredUser.getEmail());
    }

    @Test
    public void testAuthenticateUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> authenticatedUser = userService.authenticateUser("test@example.com");
        assertTrue(authenticatedUser.isPresent());
    }
}
