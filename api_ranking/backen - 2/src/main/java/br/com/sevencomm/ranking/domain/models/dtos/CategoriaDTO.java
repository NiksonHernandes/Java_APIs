package br.com.sevencomm.ranking.domain.models.dtos;

import br.com.sevencomm.ranking.domain.models.Categoria;
import br.com.sevencomm.ranking.domain.models.ClubeUser;
import br.com.sevencomm.ranking.domain.models.User;
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
public class CategoriaDTO {

    private Long categoriaId;
    private String nome;
    private Long clubeId;

    public static CategoriaDTO toDTO(Categoria obj) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        CategoriaDTO dto = modelMapper.map(obj, CategoriaDTO.class);

        return dto;
    }

    public static List<CategoriaDTO> toListDTO(List<Categoria> obj) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        List<CategoriaDTO> dtos = obj
                .stream()
                .map(x -> modelMapper.map(x, CategoriaDTO.class))
                .collect(Collectors.toList());

        return dtos;
    }

}
