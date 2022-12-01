package br.com.sevencomm.usuario.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data //gerar os métodos getters, setters, equals, hashCode e o toString
@NoArgsConstructor //Para criar um construtor vazio
@AllArgsConstructor //Para criar um construtor com todos os atributos
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String username; //login = considerar o username

    @JsonIgnore
    private String password;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //que é para informar que o atributo com o nome user na entity Carro que é dona do relacionamento.
    @JsonManagedReference
    private List<Carro> carroList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles", //meu join table se chama user_roles
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles; //meu usuário tem uma lista de roles

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return roles; }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { return true; }

}