package se.lexicon.g49marketplace.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g49marketplace.domain.entity.Advertisement;
import se.lexicon.g49marketplace.domain.entity.User;
import se.lexicon.g49marketplace.service.AdvertisementService;
import se.lexicon.g49marketplace.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AdvertisementController {
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/ads")
    public ResponseEntity<Advertisement> createAd(@RequestBody @Valid Advertisement ad) {
        // Register the ad and the user if not exist
        return ResponseEntity.ok(advertisementService.createAdvertisement(ad));
    }
    @GetMapping("/ads")
    public ResponseEntity<List<Advertisement>> getAds() {
        return ResponseEntity.ok(advertisementService.getActiveAdvertisements());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody @Valid User user) {
        Optional<User> authenticatedUser = userService.authenticateUser(user.getEmail());
        if (authenticatedUser.isPresent()) {
            return ResponseEntity.ok("User authenticated");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
