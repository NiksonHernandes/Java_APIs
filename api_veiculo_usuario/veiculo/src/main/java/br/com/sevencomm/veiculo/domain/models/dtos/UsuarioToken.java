package br.com.sevencomm.veiculo.domain.models.dtos;

import br.com.sevencomm.veiculo.domain.models.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioToken {

    private String login;
    private String nome;
    private String email;

    // token jwt
    private String token;

    public static UsuarioToken toDTO(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        UsuarioToken dto = modelMapper.map(usuario, UsuarioToken.class);

        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();

        return m.writeValueAsString(this);
    }
}
