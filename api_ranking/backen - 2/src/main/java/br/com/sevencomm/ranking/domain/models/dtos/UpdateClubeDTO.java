package br.com.sevencomm.ranking.domain.models.dtos;

import br.com.sevencomm.ranking.domain.models.Clube;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClubeDTO {

    private Long id;
    private String nome;

    public static UpdateClubeDTO toDTO(Clube obj) {
        ModelMapper modelMapper = new ModelMapper();
        UpdateClubeDTO dto = modelMapper.map(obj, UpdateClubeDTO.class); //transforma obj passado para ClubeDTO

        return dto;
    }
}
