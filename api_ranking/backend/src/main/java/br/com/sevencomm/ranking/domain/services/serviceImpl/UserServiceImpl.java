package br.com.sevencomm.ranking.domain.services.serviceImpl;

import br.com.sevencomm.ranking.data.repositories.RolesRepository;
import br.com.sevencomm.ranking.data.repositories.UserRepository;
import br.com.sevencomm.ranking.domain.models.User;
import br.com.sevencomm.ranking.domain.models.dtos.SignUpDTO;
import br.com.sevencomm.ranking.domain.models.dtos.UserDTO;
import br.com.sevencomm.ranking.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private RolesRepository _roleRepository;

    //MÉTODOS DO ADMINSTRADOR
    @Override
    @Transactional
    public User create(User obj) {
        User user = new User();
        user.setNome_completo(obj.getNome_completo());
        user.setUsername(obj.getUsername());
        user.setEmail(obj.getEmail());

        //criptografa a senha
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = bCryptPasswordEncoder.encode(obj.getSenha());
        user.setSenha(senhaCriptografada);

        //adicionar a role USER
        user.setRoles(Arrays.asList(_roleRepository.findByNome("ROLE_USER")));

        user = _userRepository.save(user);

        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<User> userOptional = _userRepository.findById(id);

        if (!(userOptional.isPresent()))
            throw new IllegalArgumentException("Id inválido");

        _userRepository.deleteById(id);
    }

    @Override
    public List<User> list() {
        return _userRepository.findAll();
    }

    @Override
    public User read(Long id) {
        Optional<User> userOptional = _userRepository.findById(id);

        if (!(userOptional.isPresent()))
            throw new IllegalArgumentException("Id inválido");

        return userOptional.get();
    }

    @Override
    public User update(User obj) {
        Optional<User> userOptional = _userRepository.findById(obj.getId());

        if (!(userOptional.isPresent()))
            throw new IllegalArgumentException("Id inválido");

        return _userRepository.save(obj);
    }


//----------------------------------------------------------------------------

    @Override
    @Transactional
    public void deleteUserByID(Long userId) {
        Optional<User> userOptional = _userRepository.findById(userId);

        if (!(userOptional.isPresent()))
            throw new IllegalArgumentException("Id do usuário não encontrado!");

        _userRepository.delete(userOptional.get());

    }

    @Override
    public UserDTO readUserById(Long userId) {
        Optional<User> userOptional = _userRepository.findById(userId);

        if (!userOptional.isPresent())
            throw new IllegalArgumentException("Id do usuário não encontrado!");

        return UserDTO.toDTO(userOptional.get());
    }

    @Override
    public List<UserDTO> search() {
        List<User> users = _userRepository.findAll();

        if (users.isEmpty())
            throw new IllegalArgumentException("Lista de usuários vazia!");

        List<UserDTO> userDTOList = new ArrayList<>();
        for (User u : users)
            userDTOList.add(UserDTO.toDTO(u));

        return userDTOList;
    }

    @Override
    @Transactional
    public UserDTO signUp(SignUpDTO signUpDTO) {
        if (_userRepository.existsByUsername(signUpDTO.getUsername()))
            throw new IllegalArgumentException("Username já existente!");

        if (signUpDTO.getNome_completo().equals("") || signUpDTO.getUsername().equals("") ||
        signUpDTO.getEmail().equals("") || signUpDTO.getSenha().equals(""))
            throw new IllegalArgumentException("Campos Vazios!");

        if (!signUpDTO.getSenha().equals(signUpDTO.getSenhaConfirmacao()))
            throw new IllegalArgumentException("Senhas Diferentes!");

        //converter DTO para salvar no banco de dados
        User user = new User();
        user.setNome_completo(signUpDTO.getNome_completo());
        user.setUsername(signUpDTO.getUsername());
        user.setEmail(signUpDTO.getEmail());

        //criptografa a senha
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = bCryptPasswordEncoder.encode(signUpDTO.getSenha());
        user.setSenha(senhaCriptografada);

        //adicionar a role USER
        user.setRoles(Arrays.asList(_roleRepository.findByNome("ROLE_USER")));

        user = _userRepository.save(user);

        return UserDTO.toDTO(user);
    }

    @Override
    public UserDTO updateUser(SignUpDTO signUpDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();//pega o usuário autenticado

        Optional<User> userOptional = _userRepository.findById(signUpDTO.getId());

        if (!userOptional.isPresent())
            throw new IllegalArgumentException("Id do usuário não encontrado!");

        if (signUpDTO.getNome_completo().trim().equals("") || signUpDTO.getUsername().trim().equals("") ||
                signUpDTO.getEmail().trim().equals("") || signUpDTO.getSenha().trim().equals(""))
            throw new IllegalArgumentException("Campos Vazios!");

        if (!signUpDTO.getSenha().equals(signUpDTO.getSenhaConfirmacao()))
            throw new IllegalArgumentException("Senhas Diferentes!");

        //Não pode alterar o usuário do amiguinho
        if (!userOptional.get().getId().equals(currentUser.getId()))
            throw new IllegalArgumentException("Não permitido! Você só pode alterar o seu usuário." +
                    " Seu id é: " + currentUser.getId());

        User user = userOptional.get();
        user.setNome_completo(signUpDTO.getNome_completo());
        user.setUsername(signUpDTO.getUsername());
        user.setEmail(signUpDTO.getEmail());

        //criptografa a senha
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = bCryptPasswordEncoder.encode(signUpDTO.getSenha());
        user.setSenha(senhaCriptografada);

        user = _userRepository.save(user);

        return UserDTO.toDTO(user);
    }

}
