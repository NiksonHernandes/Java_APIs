package br.com.sevencomm.usuario.domain.services;
//Lista de serviços fornecidos pelo componente (UserServiceImpl)

import br.com.sevencomm.usuario.domain.models.Carro;
import br.com.sevencomm.usuario.domain.models.User;
import br.com.sevencomm.usuario.domain.models.dtos.CarroDTO;
import br.com.sevencomm.usuario.domain.models.dtos.SignUpDTO;

import java.util.List;

public interface UserService {

    User deleteUserById(Long userId);
    List<User> listAllUser();
    User signUp(SignUpDTO signUpDTO);
    User updateUserById(Long userId, SignUpDTO signUpDTO);

}


