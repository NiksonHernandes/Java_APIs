package com.example.exemplo.domain.services;

import com.example.exemplo.domain.models.User;
import com.example.exemplo.domain.models.dtos.SignUpDTO;

import java.util.List;

public interface UserService {

    List<User> listAllUser();

    User signUp(SignUpDTO signUpDTO);
}
