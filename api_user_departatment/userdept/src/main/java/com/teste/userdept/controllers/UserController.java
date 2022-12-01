package com.teste.userdept.controllers;

import com.teste.userdept.entities.User;
import com.teste.userdept.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //vira um controlador rest
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    //endpoint
    @GetMapping
    public List<User> findAll(){ //vai no BD e retorna com todos os usuários
        List<User> result = repository.findAll();
        return result;
    }

    @GetMapping("/{id}")
    public User findByID(@PathVariable Long id){
        User result = repository.findById(id).get();
        //retorna um Optional, para pegar o usuário que ta la dentro, coloco o .get()
        return result;
    }

    @PostMapping
    public User insert(@RequestBody User user){
        User result = repository.save(user);
        return result;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        repository.deleteById(id);

        return "Carro deletado com sucesso!";
    }



}
