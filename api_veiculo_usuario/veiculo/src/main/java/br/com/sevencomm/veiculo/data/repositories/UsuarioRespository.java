package br.com.sevencomm.veiculo.data.repositories;

import br.com.sevencomm.veiculo.domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRespository extends JpaRepository<Usuario, Long> {

    Boolean existsByLogin(String Login);
    Usuario findByLogin(String Login);

}
