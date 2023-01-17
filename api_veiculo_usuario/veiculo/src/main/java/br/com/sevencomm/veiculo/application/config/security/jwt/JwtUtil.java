package br.com.sevencomm.veiculo.application.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

//gera os tokens e faz a validação
public class JwtUtil {

    // Chave com algoritmo HS512 com 512 bits
    // http://www.allkeysgenerator.com
    private static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

    public static Claims getClaims(String token) {
        byte[] signingKey = JwtUtil.JWT_SECRET.getBytes();

        token = token.replace("Bearer ", "");

        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token).getBody();
    }

    public static String getLogin(String token) {
        Claims claims = getClaims(token); //pega as credenciasis
        if (!isNull(claims)) {
            return claims.getSubject(); //retorna o Subject, usado para criar o Token
        }
        return null;
    }

    public static List<GrantedAuthority> getRoles(String token) {
        Claims claims = getClaims(token);
        if (claims == null) {
            return null;
        }
        return ((List<?>) claims
                .get("rol")).stream()
                .map(authority -> new SimpleGrantedAuthority((String) authority))
                //converte para uma lista de GrantedAuthority para recuperar as roles
                .collect(Collectors.toList());
    }

    public static boolean isTokenValid(String token) {
        Claims claims = getClaims(token); //recupera os dados do usuário
        if (nonNull(claims)) { //verifica se n esta nula
            String login = claims.getSubject(); //recupera o Subject, login
            Date expiration = claims.getExpiration(); //recupera data de expiração
            Date now = new Date(System.currentTimeMillis());
            return login != null && expiration != null && now.before(expiration); //veriica se n expirou
        }
        return false;
    }

    public static String createToken(UserDetails user) {
        //pega a lista de roles
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority) //Method Reference -> Chama o método GrandAuthority passando
                //como parametro o getAuthority, ou seja, declarou o método sem invocá-lo explicitamente.
                //map vai gerar um novo array com os GetAuthoruity
                //Method reference retorna o nome da role
                .collect(Collectors.toList());  //pega o retorno do stream e gera uma nova lista, tem
        //que usar o método collect para isso, ou seja, gera uma nvoa lista com o resultado das operações anteriores

        byte[] signingKey = JwtUtil.JWT_SECRET.getBytes(); //pega os bytes da chave de criptografia

        //Para fins de teste, defini o JWT com tempo de expiração de 1 minuto e 44 segundos
        //float days = (float) 0.001;
        //float time    /*milis*/;
        //time = days * 24 /*horas*/ * 60 /*min*/ * 60 /*seg*/ * 1000;
        //Date expiration = new Date((long) (System.currentTimeMillis() + time)); //valido por 10 dias

        int days = 10;
        long time = days * 24 /*horas*/ * 60 /*min*/ * 60 /*seg*/ * 1000  /*milis*/;
        Date expiration = new Date(System.currentTimeMillis() + time); //valido por 10 dias
//        System.out.println(expiration);

        return Jwts.builder() //chama a API da lib JWT
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512) //pede pra assinar com a chave já informada
                .setSubject(user.getUsername()) //passa o usuário, fala se ele é adm ou user
                .setExpiration(expiration) //data de expiração
                .claim("rol", roles) //dentri do TokenJWT vc informa as roles aqui
                .compact(); //manda gerar e compactar
    }

    public static String getAuthLogin() {
        UserDetails user = getUserDetails();
        if(user != null){
            return user.getUsername();
        }
        return null;
    }

    public static UserDetails getUserDetails(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.getPrincipal() != null){
            return (UserDetails) auth.getPrincipal();
        }
        return null;
    }
}
