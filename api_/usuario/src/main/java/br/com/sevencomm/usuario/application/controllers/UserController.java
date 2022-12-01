package br.com.sevencomm.usuario.application.controllers;

import br.com.sevencomm.usuario.domain.models.dtos.CarroDTO;
import br.com.sevencomm.usuario.domain.models.dtos.SignUpDTO;
import br.com.sevencomm.usuario.domain.services.CarroService;
import br.com.sevencomm.usuario.domain.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService _userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody @Valid SignUpDTO singUpDTO) {
        try {
            /*UsuarioModel usuarioModel = new UsuarioModel();
            BeanUtils.copyProperties(usuarioDto, usuarioModel);*/
            return ResponseEntity.ok(_userService.signUp(singUpDTO));
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

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") Long userId) {
        try {
            return ResponseEntity.ok(_userService.deleteUserById(userId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("update-user/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> updateUserById(@PathVariable(value = "id") Long userId,
                                         @RequestBody @Valid SignUpDTO signUpDTO) {

        try {
            return ResponseEntity.ok(_userService.updateUserById(userId, signUpDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}




//-----------------------COMENTÁRIOS:-----------------------
//getById
//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    /*@GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id){
        try{
            return ResponseEntity.ok(usuarioService.getById(id).get());
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @PostMapping ResponseEntity<Object> save(@RequestBody @Valid UsuarioDto usuarioDto){

        //verifica Email - Método customizado
        public boolean verificaEmail(String email) {
            return usuarioRepository.existsByEmail(email);
        }

        //verifica CPF - Método customizado
        public boolean verificaCpf(String cpf) {
            return usuarioRepository.existsByCpf(cpf);
        }

        //verifica Telefone - Método customizado
        public boolean verificaTelefone(String telefone) {
            return usuarioRepository.existsByTelefone(telefone);
        }
        UsuarioModel usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
    }*/
//recebe todos os campos do usuarioDto, justamente para fazer as verificações e depois quando
//for passar para o BD a gnt converte o DTO em Model, pq o banco vai receber a model, não o DTO.
//Quem faz essa conversão é o: BeanUtils.copyProperties(usuarioDto, usuarioModel); oq vai ser convertido,
//e no que vai ser convertido
//@Valid para validar os DTO's