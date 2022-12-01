package br.com.sevencomm.veiculo.application.config.security;
//responsável por buscar o usuário no b.d que será autenticado pelo username

import br.com.sevencomm.veiculo.data.repositories.UsuarioRespository;
import br.com.sevencomm.veiculo.domain.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRespository _usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = _usuarioRepository.findByLogin(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return usuario; //para retornar nossa classe usuario, temos q implementar UserDetails nela, pq tenho q retornar um UserDetails
    }

}
