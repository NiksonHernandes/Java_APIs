package br.com.sevencomm.usuario.application.config.security;
//busca o usuário no b.d. utilizando o username

import br.com.sevencomm.usuario.data.repositories.UserRepository;
import br.com.sevencomm.usuario.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository _userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = _userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return user;
    }

}







//    //acessa o Repository e consulta se o username existe no b.d
//    //retorna um OBJ que implementa UserDetails
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado " + username));
//        return user;
//
//    }

