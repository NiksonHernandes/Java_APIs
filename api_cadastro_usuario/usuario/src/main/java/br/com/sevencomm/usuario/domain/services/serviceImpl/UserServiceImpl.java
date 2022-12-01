package br.com.sevencomm.usuario.domain.services.serviceImpl;

import br.com.sevencomm.usuario.data.repositories.RoleRepository;
import br.com.sevencomm.usuario.data.repositories.UserRepository;
import br.com.sevencomm.usuario.domain.models.Role;
import br.com.sevencomm.usuario.domain.models.User;
import br.com.sevencomm.usuario.domain.models.dtos.SignUpDTO;
import br.com.sevencomm.usuario.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service //BIM do Spring - para facilitar a injecao de dependencia
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private RoleRepository _roleRepository;

    //quando é método CONSTRUTIVO ou DESTRUTIVO, pq garante a integridade: @Transactional
    @Transactional
    public User signUp(SignUpDTO signUpDTO) {
        //verifica s o E-mail já existe
        if (_userRepository.existsByEmail(signUpDTO.getEmail())) {
            throw new IllegalArgumentException("E-mail já existente!");
        }

        if (_userRepository.existsByUsername(signUpDTO.getUsername())) {
            throw new IllegalArgumentException("Username já existente!");
        }

        if (signUpDTO.getPassword() == null || Objects.equals(signUpDTO.getPassword(), "")) {
            throw new IllegalArgumentException("Cadastre uma senha");
        }

        if (!Objects.equals(signUpDTO.getPassword(), signUpDTO.getPasswordConfirm())) {
            throw new IllegalArgumentException("Senha diferente");
        }

        User user = new User();
        user.setNome(signUpDTO.getNome());
        user.setUsername(signUpDTO.getUsername());
        user.setEmail(signUpDTO.getEmail());

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = bCryptPasswordEncoder.encode(signUpDTO.getPassword());
        user.setPassword(senhaCriptografada);

        user.setRoles(Arrays.asList(_roleRepository.findByNome("ROLE_USER"))); //Retorna uma lista de tamanho fixo

        user = _userRepository.save(user);

        return user;
    }

    public List<User> listAllUser() {
        List<User> users = _userRepository.findAll();

        if (users.isEmpty()) {
            throw new IllegalArgumentException("Lista Vazia!");
        }

        return users;
    }

    @Transactional
    public User deleteUserById(Long userId) {
        Optional<User> optionalUser = _userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            _userRepository.delete(user);
            return user;
        }
        throw new IllegalArgumentException("Id inválido");
    }

    public User updateUserById(Long userId, SignUpDTO signUpDTO) {
        Optional<User> optionalUser = _userRepository.findById(userId);

        if (optionalUser.isPresent()) {

            if (signUpDTO.getEmail().equals("") || signUpDTO.getUsername().equals("")
            || signUpDTO.getPassword().equals("") || signUpDTO.getPasswordConfirm().equals("")) {

                throw new IllegalArgumentException("Campos vazios");
            }

            User _user = optionalUser.get();
            _user.setNome(signUpDTO.getNome());
            _user.setUsername(signUpDTO.getUsername());
            _user.setEmail(signUpDTO.getEmail());

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String senhaCriptografada = bCryptPasswordEncoder.encode(signUpDTO.getPassword());
            _user.setPassword(senhaCriptografada);

            _user = _userRepository.save(_user);

            return _user;
        }

        throw new IllegalArgumentException("Id inválido");
    }
}


//-----------------------COMENTÁRIOS:-----------------------
//
//    //getAll
//    public List<Perfil> getAll() {
//        return usuarioRepository.findAll();
//    }
//
//    //getByID
//    public Optional<Perfil> getById(Long id) {
//        Optional<Perfil> usuarioModelOptional = usuarioRepository.findById(id);
//        if(!usuarioModelOptional.isPresent()){
//            throw new IllegalArgumentException("Conflict: ID não encontrado! (getById)");
//        }
//        return usuarioModelOptional;
//    }
//
//    //deleteByID
//    @Transactional
//    public Optional<Perfil> deleteById(Long id) {
//        Optional<Perfil> usuarioModelOptionalDelete = getById(id);
//        if(!usuarioModelOptionalDelete.isPresent()){
//            throw new IllegalArgumentException("Conflict: ID não encontrado! (deleteById)");
//        }
//        usuarioRepository.delete(usuarioModelOptionalDelete.get());
//        return  usuarioModelOptionalDelete;
//    }
//
//    //update
//    public Perfil updateUser(Long id, UsuarioDto usuarioDto) {
//        Optional<Perfil> usuarioModelOptionalUpdate = getById(id);
//        if(!usuarioModelOptionalUpdate.isPresent()){
//            throw new IllegalArgumentException("Conflict: ID não encontrado! (Update)");
//        }
//
//        //converte DTO para Modeel
//        Perfil _usuarioModel = usuarioModelOptionalUpdate.get();
//        _usuarioModel.setNome(usuarioDto.getNome());
//        _usuarioModel.setSobrenome(usuarioDto.getSobrenome());
//        _usuarioModel.setEmail(usuarioDto.getEmail());
//        _usuarioModel.setCpf(usuarioDto.getCpf());
//        _usuarioModel.setTelefone(usuarioDto.getTelefone());
//
//        _usuarioModel = usuarioRepository.save(_usuarioModel);
//        return _usuarioModel;
//    }



    /*
    public UsuarioModel save(UsuarioModel usuarioModel){
        return usuarioRepository.save(usuarioModel);
    }*/