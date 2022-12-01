package br.com.sevencomm.veiculo.domain.models.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class SsoDTO {

    private String access_token;
    private UsuarioToken current_user;

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();

        return m.writeValueAsString(this);
    }
}
