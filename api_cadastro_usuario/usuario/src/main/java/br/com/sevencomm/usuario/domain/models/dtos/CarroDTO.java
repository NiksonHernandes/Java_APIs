package br.com.sevencomm.usuario.domain.models.dtos;
//usado para recepção e validação dos dados

import br.com.sevencomm.usuario.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarroDTO {

    private Long id;
    private String nome;
    private String tipo;
    private User user;

}
