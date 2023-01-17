package br.com.sevencomm.veiculo.application.config.security;

import br.com.sevencomm.veiculo.application.config.security.jwt.JwtAuthenticationFilter;
import br.com.sevencomm.veiculo.application.config.security.jwt.JwtAuthorizationFilter;
import br.com.sevencomm.veiculo.application.config.security.jwt.handler.UnauthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService") //to falando pra ele utilizar o a class com esse identificado no qualifier
    private UserDetailsService _userDetailsService;

    @Autowired
    private UnauthorizedHandler unauthorizedHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authManager = authenticationManager();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login").permitAll()
                //.antMatchers(HttpMethod.GET, "/veiculo").permitAll()
                .antMatchers(HttpMethod.POST, "/usuario/sign-up").permitAll()
                .anyRequest().authenticated()
                .and().cors().and().csrf().disable() //desabilita o springSecurity de cuidar do CORS
                //permitir em todas as requisições o cors
                //.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                //.and()
                .addFilter(new JwtAuthenticationFilter(authManager)) //filtro que faz o login do usuário
                .addFilter(new JwtAuthorizationFilter(authManager, _userDetailsService))//filtro feito para autorizar, verificar se o Token passado é válido
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(_userDetailsService).passwordEncoder(encoder); //chama userDetailsServiceImpl
    }

}
