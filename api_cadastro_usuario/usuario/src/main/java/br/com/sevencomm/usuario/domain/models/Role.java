package br.com.sevencomm.usuario.domain.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.List;

@Entity
public class Role implements GrantedAuthority {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String nome;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @Override
    public String getAuthority() { return nome; }

}














//@Entity
//public class Role implements GrantedAuthority, Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long roleId;
//    @Enumerated(EnumType.STRING)//quando for salvar no B.D n quero salvar como enum, quero salvar
//    //como string, quero trabalhar como Enum só aq na aplicação
//    @Column(nullable = false, unique = true)
//    private RoleName roleName;
//
//    @Override
//    public String getAuthority() {
//        return this.roleName.toString(); //transforma de Enum para String, pq o retorno do método é uma string
//    }
//
//    public Long getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(Long roleId) {
//        this.roleId = roleId;
//    }
//
//    public RoleName getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(RoleName roleName) {
//        this.roleName = roleName;
//    }
//}
