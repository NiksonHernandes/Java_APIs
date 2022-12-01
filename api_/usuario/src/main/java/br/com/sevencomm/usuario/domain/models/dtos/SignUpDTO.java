package br.com.sevencomm.usuario.domain.models.dtos;

//usado para recepção e validação dos dados

import br.com.sevencomm.usuario.domain.models.Carro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

    private String nome;
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private List<Carro> carroList;

}
