package br.com.sevencomm.veiculo.domain.services;

import br.com.sevencomm.veiculo.domain.models.Usuario;
import br.com.sevencomm.veiculo.domain.models.dtos.SignUpDTO;

import java.util.List;

public interface UsuarioService {

    List<Usuario> listAllUsuario();
    Usuario signUp(SignUpDTO signUpDTO);

}
