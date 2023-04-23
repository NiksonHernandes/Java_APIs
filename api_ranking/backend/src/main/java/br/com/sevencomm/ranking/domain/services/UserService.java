package br.com.sevencomm.ranking.domain.services;

import br.com.sevencomm.ranking.domain.models.User;
import br.com.sevencomm.ranking.domain.models.dtos.SignUpDTO;
import br.com.sevencomm.ranking.domain.models.dtos.UserDTO;

import java.util.List;

public interface UserService {

    //MÉTODOS DO ADMINSTRADOR
    User create(User obj);
    void delete(Long id);
    List<User> list();
    User read(Long id);
    User update(User obj);

    void deleteUserByID(Long userId);
    UserDTO readUserById(Long userId);
    List<UserDTO> search();
    UserDTO signUp(SignUpDTO signUpDTO);
    UserDTO updateUser(SignUpDTO signUpDTO);

}
