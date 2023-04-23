package br.com.sevencomm.ranking.domain.models.dtos;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Data
public class UpdateCategoriaDTO {

    private Long categoriaId;
    private String nome;

    public static UpdateCategoriaDTO toDTO(UpdateCategoriaDTO obj) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        UpdateCategoriaDTO dto = modelMapper.map(obj, UpdateCategoriaDTO.class);

        return dto;
    }

}