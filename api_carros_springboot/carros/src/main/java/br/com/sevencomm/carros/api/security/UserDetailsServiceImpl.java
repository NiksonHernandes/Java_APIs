package br.com.sevencomm.carros.api.security;
//responsável por fazer a autenticação dos usuários na API

import br.com.sevencomm.carros.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import br.com.sevencomm.carros.domain.UserRepository;

@org.springframework.stereotype.Service(value="userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{ //implementar os métodos da interface

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); -> para gerar o encoder e a senha em hash
		
		User user = userRepository.findUserByLogin(username);
		if(user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		return user;
	}
}
