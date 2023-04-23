package br.com.sevencomm.ranking.application.config.security;

import br.com.sevencomm.ranking.data.repositories.UserRepository;
import br.com.sevencomm.ranking.domain.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository _userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) { _userRepository = userRepository; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User _user = _userRepository.findByUsername(username);

        if (_user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return _user;
    }
}
