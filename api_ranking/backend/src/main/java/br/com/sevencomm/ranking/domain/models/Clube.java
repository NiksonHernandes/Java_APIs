package br.com.sevencomm.ranking.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Clube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    //User dono do clube (quem criou o clube)
    @ManyToOne
    private User ownerUser;

    @OneToMany(mappedBy = "clube", cascade = CascadeType.ALL)
    private List<Categoria> categorias;
    
    //Lista de usuários que estão nesse clube
    @OneToMany(mappedBy = "clube", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ClubeUser> clubeUsers;

}
