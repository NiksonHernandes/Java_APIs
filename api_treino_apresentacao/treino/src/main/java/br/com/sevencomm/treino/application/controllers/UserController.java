package br.com.sevencomm.treino.application.controllers;

import br.com.sevencomm.treino.data.repositories.UserRepository;
import br.com.sevencomm.treino.domain.models.dtos.SignUpDTO;
import br.com.sevencomm.treino.domain.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService _userService;

    public UserController(UserService userService) {
        _userService= userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpDTO signUpDTO) {
        try{
            return ResponseEntity.ok(_userService.signUp(signUpDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> listAllUser() {
        try {
            return ResponseEntity.ok(_userService.listAllUser());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") Long userId) {
        try {
            return ResponseEntity.ok(_userService.getUserById(userId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") Long userId) {
        try {
            return ResponseEntity.ok(_userService.deleteUserById(userId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/update-user/{id}")
    public  ResponseEntity<Object> updateCarroById(@PathVariable(value = "id") Long userId,
                                                   @RequestBody SignUpDTO signUpDTO) {
        try {
            return ResponseEntity.ok(_userService.updateCarroById(userId, signUpDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
