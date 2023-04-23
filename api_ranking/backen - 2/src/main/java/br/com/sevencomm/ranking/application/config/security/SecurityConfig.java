package br.com.sevencomm.ranking.application.config.security;

import br.com.sevencomm.ranking.application.config.security.jwt.JwtAuthenticationFilter;
import br.com.sevencomm.ranking.application.config.security.jwt.JwtAuthorizationFilter;
import br.com.sevencomm.ranking.application.config.security.jwt.handler.AccessDeniedHandler;
import br.com.sevencomm.ranking.application.config.security.jwt.handler.UnauthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl _userDetailsServiceImpl;

    @Autowired
    private UnauthorizedHandler _unauthorizedHandler;

    @Autowired
    private AccessDeniedHandler _accesDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authManager = authenticationManager();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/user/sign-up").permitAll()
                .anyRequest().authenticated()
                .and().cors().and().csrf().disable() //desabilita o springSecurity de cuidar do CORS
                .addFilter(new JwtAuthenticationFilter(authManager)) //filtro que faz o login do usuário
                .addFilter(new JwtAuthorizationFilter(authManager, _userDetailsServiceImpl))//filtro feito para autorizar, verificar se o Token passado é válido
                .exceptionHandling()
                .accessDeniedHandler(_accesDeniedHandler)
                .authenticationEntryPoint(_unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(_userDetailsServiceImpl).passwordEncoder(encoder);
    }

//    @Bean //para dizer pro Spring gerenciar ele e podermos criar ponto de injeção nele
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
