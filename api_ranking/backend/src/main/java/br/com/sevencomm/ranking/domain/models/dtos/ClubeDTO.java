package br.com.sevencomm.ranking.domain.models.dtos;

import br.com.sevencomm.ranking.domain.models.Clube;
import br.com.sevencomm.ranking.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubeDTO {

    private String nome;

    public static ClubeDTO toDTO(Clube obj) {
        ModelMapper modelMapper = new ModelMapper();
        ClubeDTO dto = modelMapper.map(obj, ClubeDTO.class); //transforma obj passado para ClubeDTO

        return dto;
    }

}
