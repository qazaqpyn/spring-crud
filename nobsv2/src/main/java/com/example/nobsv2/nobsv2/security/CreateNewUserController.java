package com.example.nobsv2.nobsv2.security;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateNewUserController {

    private final PasswordEncoder encoder;

    private final CustomUserRepository customUserRepository;

    public CreateNewUserController(PasswordEncoder encoder, CustomUserRepository customUserRepository) {
        this.encoder = encoder;
        this.customUserRepository = customUserRepository;
    }

    @PostMapping("user/create")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser user) {
        Optional<CustomUser> optionalUser = this.customUserRepository.findById(user.getUsername());
        if (!optionalUser.isPresent()) {
            CustomUser newUser = new CustomUser(user.getUsername(), encoder.encode(user.getPassword()));
            this.customUserRepository.save(newUser);

            return ResponseEntity.ok("created");
        }

        return ResponseEntity.badRequest().body("failure");

    }
}
