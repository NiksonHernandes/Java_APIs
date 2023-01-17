package br.com.sevencomm.treino.application.config.security;

import br.com.sevencomm.treino.data.repositories.UserRepository;
import br.com.sevencomm.treino.domain.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository _userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException { //recebe o username e busca na BD
        User _user = _userRepository.findByLogin(login);

        if (_user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new org.springframework.security.core.userdetails.User(_user.getLogin(), _user.getSenha(), true, true, true, true, _user.getAuthorities());
    }
}
