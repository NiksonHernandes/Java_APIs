package com.example.exemplo.domain.services.servicesImpl;

import com.example.exemplo.data.repositories.UserRepository;
import com.example.exemplo.domain.models.User;
import com.example.exemplo.domain.models.dtos.SignUpDTO;
import com.example.exemplo.domain.services.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository _userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Override
    public List<User> listAllUser() {
        List<User> listUsers = _userRepository.findAll();

        if(listUsers.isEmpty()) {
            throw new IllegalArgumentException("Lista Vazia!");
        }

        return listUsers;
    }

    @Override
    @Transactional
    public User signUp(SignUpDTO signUpDTO) {
        if(_userRepository.existsByLogin(signUpDTO.getLogin())) {
            throw new IllegalArgumentException("Login já existente!");
        }

        if (signUpDTO.getNome().equals("") || signUpDTO.getEmail().equals("") ||
                signUpDTO.getLogin().equals("") || signUpDTO.getSenha().equals("")) {
            throw new IllegalArgumentException("Campos vazios");
        }

        if (!signUpDTO.getSenha().equals(signUpDTO.getSenhaConfirmacao())) {
            throw new IllegalArgumentException("Senhas diferentes");
        }

        //converter DTO para salvar no banco de dados
        User _user = new User();
        _user.setNome(signUpDTO.getNome());
        _user.setEmail(signUpDTO.getEmail());
        _user.setLogin(signUpDTO.getLogin());
        _user.setSenha(signUpDTO.getSenha());

        _user = _userRepository.save(_user);

        return _user;
    }
}
