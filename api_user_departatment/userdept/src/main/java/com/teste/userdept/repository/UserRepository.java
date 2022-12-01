package com.teste.userdept.repository;

import com.teste.userdept.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

//acessa os dados da Entidade User
public interface UserRepository extends JpaRepository<User, Long> {

}
