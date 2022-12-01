package br.com.sevencomm.veiculo.data.repositories;

import br.com.sevencomm.veiculo.domain.models.Role;
import br.com.sevencomm.veiculo.domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByNome(String nome);

}
