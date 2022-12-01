package br.com.sevencomm.veiculo.domain.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDTO {

    private String nome;
    private String tipo;

}
