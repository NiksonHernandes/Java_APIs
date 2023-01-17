package br.com.sevencomm.treino.application.config.security;

import br.com.sevencomm.treino.data.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //estamos desligando as config default do security e vamos personalizar as nossas
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl _userDetailsServiceImpl;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {

        _userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and() //usado para unir as configurações
                .authorizeHttpRequests() //autorização de requições
                .anyRequest().authenticated() //para qualquer requisição vamos authenticar
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                /*
                PRIMEIRO ISSO
                .inMemoryAuthentication() //ligando authenticação em memória
                .withUser("user") //usuario
                .password(passwordEncoder().encode("123")) //senha sempre criptografada
                .roles("ADMIN"); //role
                */
                .userDetailsService(_userDetailsServiceImpl) //ele vai usar o nosso User|DetailsSerrvice
                .passwordEncoder(passwordEncoder());
    }

    @Bean //para dizer pro Spring gerenciar ele e podermos criar ponto de injeção nele
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
