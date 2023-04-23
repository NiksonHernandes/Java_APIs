package br.com.sevencomm.ranking.domain.models.dtos;

import br.com.sevencomm.ranking.domain.models.Categoria;
import br.com.sevencomm.ranking.domain.models.CategoriaUser;
import br.com.sevencomm.ranking.domain.models.ClubeUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaUserDTO {

    private int posicao;
    private int pontos;
    private Long userId;
    private Long categoriaId;

    public static CategoriaDTO toDTO(CategoriaUser obj) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        CategoriaDTO dto = modelMapper.map(obj, CategoriaDTO.class);

        return dto;
    }

    public static List<CategoriaUserDTO> toListDTO(List<CategoriaUser> obj) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        List<CategoriaUserDTO> dtos = obj
                .stream()
                .map(x -> modelMapper.map(x, CategoriaUserDTO.class))
                .collect(Collectors.toList());

        return dtos;
    }


}
