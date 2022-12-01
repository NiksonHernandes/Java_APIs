package br.com.sevencomm.usuario.domain.models.dtos;

import br.com.sevencomm.usuario.domain.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class SsoDTO {

    private String access_token;
    private UserDTO current_user;

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();

        return m.writeValueAsString(this);
    }

}
