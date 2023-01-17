package br.com.sevencomm.veiculo.application.controllers;

import br.com.sevencomm.veiculo.domain.models.Usuario;
import br.com.sevencomm.veiculo.domain.models.dtos.SignUpDTO;
import br.com.sevencomm.veiculo.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*")
//@CrossOrigin -> libera requisção de qq lugar
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService _usuarioService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpDTO signUpDTO) {
        try{
            return ResponseEntity.ok(_usuarioService.signUp(signUpDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public List<Usuario> listAllUsuaio() {
        return _usuarioService.listAllUsuario();
    }
}
