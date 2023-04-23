package br.com.sevencomm.ranking.application.controllers;

import br.com.sevencomm.ranking.domain.models.User;
import br.com.sevencomm.ranking.domain.models.dtos.SignUpDTO;
import br.com.sevencomm.ranking.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService _userService;

    //MÉTODOS DO ADMINSTRADOR
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody User user) {
        try{
            return ResponseEntity.ok(_userService.create(user));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam(value = "id") Long id) {
        try {
            _userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        try{
            return ResponseEntity.ok(_userService.list());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/read")
    public ResponseEntity<Object> read(@RequestParam(value = "id") Long id) {
        try {
            return ResponseEntity.ok(_userService.read(id));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/update")
    public  ResponseEntity<Object> update(@RequestBody User user) {
        try {
            return ResponseEntity.ok(_userService.update(user));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    //---------------------------------------------------------------------------------

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/delete-user-by-id")
    public ResponseEntity<Object> deleteUserById(@RequestParam(value = "userId") Long userId) {
        try {
            _userService.deleteUserByID(userId);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/read-user-by-id")
    public ResponseEntity<Object> readUserById(@RequestParam(value = "userId") Long userId) {
        try {
            return ResponseEntity.ok(_userService.readUserById(userId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/search")
    public ResponseEntity<Object> search() {
        try{
            return ResponseEntity.ok(_userService.search());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpDTO signUpDTO) {
        try{
            return ResponseEntity.ok(_userService.signUp(signUpDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/update-user")
    public  ResponseEntity<Object> updateUser(@RequestBody SignUpDTO signUpDTO) {
        try {
            return ResponseEntity.ok(_userService.updateUser(signUpDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
