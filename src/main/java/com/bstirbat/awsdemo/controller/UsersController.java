package com.bstirbat.awsdemo.controller;

import com.bstirbat.awsdemo.entity.User;
import com.bstirbat.awsdemo.model.SignUpModel;
import com.bstirbat.awsdemo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UsersController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpModel signUpModel) {
        User user = new User();
        user.setUsername(signUpModel.getUserName());
        user.setPassword(passwordEncoder.encode(signUpModel.getPassword()));

        return userRepository.save(user);
    }

    @GetMapping("/test")
    public ResponseEntity test(OAuth2Authentication authentication) {
        String username = (String) authentication.getUserAuthentication().getPrincipal();

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(410).build();
        }

        return ResponseEntity.ok().build();
    }






}
