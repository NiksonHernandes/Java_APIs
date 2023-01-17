package com.example.exemplo.application.controllers;

import com.example.exemplo.domain.models.dtos.SignUpDTO;
import com.example.exemplo.domain.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService _userService;

    public UserController(UserService userService) {
        _userService= userService;
    }

    @GetMapping()
    public ResponseEntity<Object> listAllUser() {
        try {
            return ResponseEntity.ok(_userService.listAllUser());

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpDTO signUpDTO) {
        try {
            return ResponseEntity.ok(_userService.signUp(signUpDTO));

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
