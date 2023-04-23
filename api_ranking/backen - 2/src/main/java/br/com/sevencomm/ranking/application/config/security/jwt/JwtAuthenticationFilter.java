package br.com.sevencomm.ranking.application.config.security.jwt;

import br.com.sevencomm.ranking.domain.models.User;
import br.com.sevencomm.ranking.domain.models.dtos.SsoDTO;
import br.com.sevencomm.ranking.domain.models.dtos.UserToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String AUTH_URL = "/login";

    private final AuthenticationManager _authenticationManager;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        _authenticationManager = authenticationManager;

        setFilterProcessesUrl(AUTH_URL); //fala que a URL será responsável pelo END-point de login
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        try {
            //transforma um string JSON em JAVA
            JwtLoginInput login = new ObjectMapper().readValue(request.getInputStream(), JwtLoginInput.class);
            String username = login.getUsername();
            String password = login.getSenha();

            if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                throw new BadCredentialsException("Usuário/Senha inválido!");
            }

            Authentication auth = new UsernamePasswordAuthenticationToken(username, password);

            return _authenticationManager.authenticate(auth);
        } catch (IOException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) throws IOException {
        User usuario = (User) authentication.getPrincipal();

        String jwtToken = JwtUtil.createToken(usuario);

        SsoDTO ssoDTO = new SsoDTO();
        ssoDTO.setCurrent_user(UserToken.toDTO(usuario));
        ssoDTO.setAccess_token(jwtToken);

        String json = ssoDTO.toJson();
        ServletUtil.write(response, HttpStatus.OK, json);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException error) throws IOException, ServletException {
        String json = ServletUtil.getJson("error", "Login incorreto");
        ServletUtil.write(response, HttpStatus.UNAUTHORIZED, json);
    }
}
