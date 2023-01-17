package br.com.sevencomm.treino.domain.services;

import br.com.sevencomm.treino.domain.models.User;
import br.com.sevencomm.treino.domain.models.dtos.SignUpDTO;

import java.util.List;

public interface UserService {

    User signUp(SignUpDTO signUpDTO);
    List<User> listAllUser();
    User getUserById(Long userId);
    User deleteUserById(Long userId);
    User updateCarroById(Long userId, SignUpDTO signUpDTO);

}
