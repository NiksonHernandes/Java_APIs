package br.com.sevencomm.ranking.domain.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubeListDTO {

    private Long clubeId;
    private String nomeClube;
    private String nomeOwnerClube;
    private String emailOwnerClube;

}
