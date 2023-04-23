package br.com.sevencomm.ranking.domain.models.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SsoDTO {

    private String access_token;
    private UserToken current_user;

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();

        return m.writeValueAsString(this);
    }

}
