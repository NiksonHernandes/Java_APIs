package br.com.sevencomm.ranking.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Clube clube;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL) //esta sendo mapeada em outro lugar com o nome clubes
    @JsonIgnore
    private List<CategoriaUser> users;

}
