package com.evernet.bookstore.controllers;

import com.evernet.bookstore.Services.UserService;
import com.evernet.bookstore.dto.LoginRequestDTO;
import com.evernet.bookstore.dto.RegisterRequestDTO;
import com.evernet.bookstore.dto.UserDTO;
import com.evernet.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        UserDTO userDTO = userService.registerUser(registerRequestDTO);
        return  ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO){
        try {
            Optional<String> tokenOpt = userService.authenticate(loginRequestDTO);
            return tokenOpt
                    .map(token -> ResponseEntity.ok().body(token))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed"));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }
}
