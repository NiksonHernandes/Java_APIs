package br.com.sevencomm.veiculo.domain.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

    private String nome;
    private String email;
    private String login;
    private String senha;
    private String senhaConfirmacao;

}
