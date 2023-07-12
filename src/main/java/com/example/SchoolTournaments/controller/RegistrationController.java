package com.example.SchoolTournaments.controller;

import com.example.SchoolTournaments.entity.UserEntity;
import com.example.SchoolTournaments.service.UserService;
import com.example.SchoolTournaments.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserServiceImpl userServiceImpl;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegistrationController(UserServiceImpl userServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userServiceImpl = userServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestParam String username, @RequestParam String password) {

        if (userServiceImpl.loadUserByUsername(username) != null) {
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUserName(username);
        user.setPassword(password);

        userServiceImpl.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}
