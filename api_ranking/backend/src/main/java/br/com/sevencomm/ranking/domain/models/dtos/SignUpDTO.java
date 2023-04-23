package br.com.sevencomm.ranking.domain.models.dtos;

import lombok.Data;

@Data
public class SignUpDTO {

    private Long id;
    private String nome_completo;
    private String username;
    private String email;
    private String senha;
    private String senhaConfirmacao;

}
