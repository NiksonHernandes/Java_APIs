package br.com.sevencomm.carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import br.com.sevencomm.carros.api.security.jwt.JwtAuthenticationFilter;
import br.com.sevencomm.carros.api.security.jwt.JwtAuthorizationFilter;
import br.com.sevencomm.carros.api.security.jwt.handler.AccessDeniedHandler;
import br.com.sevencomm.carros.api.security.jwt.handler.UnauthorizedHandler;

@Configuration//CONFIGURAÇÕES DE SEGURANÇA
@EnableWebSecurity //CONFIG DA SEG DA WEB
@EnableGlobalMethodSecurity(securedEnabled = true)//ativa a opção de perfil de adm ou usuário qualquer, habilita o @Secured({ROLE_ADM})
public class SecutiryConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private UnauthorizedHandler unauthorizedHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	@Qualifier("userDetailsService")//"Quando for fazer a injecao de dependencia, utilize o objeto com UserDetailsSerive"
	private UserDetailsService userDetailsService;
	
	//configura o Basic Authentication, aparece um alert em vez do formulário 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		AuthenticationManager authManager = authenticationManager();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/login").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .addFilter(new JwtAuthenticationFilter(authManager))
                .addFilter(new JwtAuthorizationFilter(authManager, userDetailsService))
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);	
        }
	
	//criar os perfil de acesso
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
				
		
//		//definindo os usuários e senha, código na própria documentação - configure
//		auth
//	 		.inMemoryAuthentication().passwordEncoder(encoder)
//	 			.withUser("user").password(encoder.encode("user")).roles("USER") 
//	 			.and()
//	 			.withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN");

	}
}
