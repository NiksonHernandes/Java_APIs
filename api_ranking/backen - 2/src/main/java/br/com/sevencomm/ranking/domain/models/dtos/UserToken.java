package br.com.sevencomm.ranking.domain.models.dtos;

import br.com.sevencomm.ranking.domain.models.Roles;
import br.com.sevencomm.ranking.domain.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@NoArgsConstructor
public class UserToken {

    private String username;
    private String nome_completo;
    private String email;

    private List<Roles> roles;

    @JsonIgnore
    private String token;

    public static UserToken toDTO(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserToken dto = modelMapper.map(user, UserToken.class); //transforma user em UserToken DTO

        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();

        return m.writeValueAsString(this);
    }

}
