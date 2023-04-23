package br.com.sevencomm.ranking.domain.models.dtos;

import br.com.sevencomm.ranking.domain.models.Categoria;
import br.com.sevencomm.ranking.domain.models.Clube;
import br.com.sevencomm.ranking.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubeReadDTO {

    private Long clubeId;
    private String nomeClube;
    private String nomeOwnerClube;
    private String emailOwnerClube;
    private List<UserDTO> listaUsuariosClube;
    private List<CategoriaDTO> listaCategorias;

    public static ClubeReadDTO toDTO(Clube obj) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ClubeReadDTO dto = modelMapper.map(obj, ClubeReadDTO.class); //transforma obj passado para ClubeDTO

        return dto;
    }

}
