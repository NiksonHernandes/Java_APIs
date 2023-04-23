package br.com.sevencomm.ranking.domain.models.dtos;

import br.com.sevencomm.ranking.domain.models.Clube;
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
public class UserDTO {

    private Long id;
    private String username;
    private String nome_completo;
    private String email;
    
    public static UserDTO toDTO(User obj) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDTO dto = modelMapper.map(obj, UserDTO.class); //transforma obj passado para ClubeDTO

        return dto;
    }

    public static List<UserDTO> toListDTO(List<ClubeUser> obj) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        List<UserDTO> dtos = obj
                .stream()
                .map(x -> modelMapper.map(x.getUser(), UserDTO.class))
                .collect(Collectors.toList());

        return dtos;
    }

}
