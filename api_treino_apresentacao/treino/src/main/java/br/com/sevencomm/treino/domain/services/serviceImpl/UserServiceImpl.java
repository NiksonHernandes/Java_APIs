package br.com.sevencomm.treino.domain.services.serviceImpl;

import br.com.sevencomm.treino.data.repositories.UserRepository;
import br.com.sevencomm.treino.domain.models.User;
import br.com.sevencomm.treino.domain.models.dtos.SignUpDTO;
import br.com.sevencomm.treino.domain.services.UserService;
import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository _userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    //Método signUp
    @Override
    @Transactional
    public User signUp(SignUpDTO signUpDTO) {
        if (_userRepository.existsByLogin(signUpDTO.getLogin())) {
            throw new IllegalArgumentException("Login já existente!");
        }

        if (signUpDTO.getNome().equals("") || signUpDTO.getEmail().equals("") ||
        signUpDTO.getLogin().equals("") || signUpDTO.getSenha().equals("")) {
            throw new IllegalArgumentException("Campos vazios");
        }

        if (!signUpDTO.getSenha().equals(signUpDTO.getSenhaConfirmacao())) {
            throw new IllegalArgumentException("Senha diferente");
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

    @Override
    public List<User> listAllUser() {
        List<User> users = _userRepository.findAll();

        if (users.isEmpty()) {
            throw new IllegalArgumentException("Lista vazia!");
        }

        return users;
    }

    @Override
    public User getUserById(Long userId) {
       Optional<User> userOptional = _userRepository.findById(userId);

       if (!userOptional.isPresent()) {
           throw new IllegalArgumentException("Id para buscar inválido");
       }

       return userOptional.get();
    }

    @Override
    public User deleteUserById(Long userId) {
        Optional<User> userOptional = _userRepository.findById(userId);

        if(userOptional.isPresent()) {
            User _user = userOptional.get();
            _userRepository.delete(_user);
            return _user;
        }

        throw new IllegalArgumentException("Id para buscar inválido");
    }

    @Override
    public User updateCarroById(Long userId, SignUpDTO signUpDTO) {
        if (signUpDTO.getNome().equals("") || signUpDTO.getEmail().equals("") ||
                signUpDTO.getLogin().equals("") || signUpDTO.getSenha().equals("")) {
            throw new IllegalArgumentException("Campos vazios");
        }

        if (!signUpDTO.getSenha().equals(signUpDTO.getSenhaConfirmacao())) {
            throw new IllegalArgumentException("Senha diferente");
        }

        User _user = getUserById(userId);
        _user.setNome(signUpDTO.getNome());
        _user.setEmail(signUpDTO.getEmail());
        _user.setLogin(signUpDTO.getLogin());
        _user.setSenha(signUpDTO.getSenha());

        _user = _userRepository.save(_user);

        return _user;
    }
}
