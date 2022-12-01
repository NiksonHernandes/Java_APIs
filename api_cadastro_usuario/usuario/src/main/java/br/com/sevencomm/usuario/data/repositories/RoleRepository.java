package br.com.sevencomm.usuario.data.repositories;

import br.com.sevencomm.usuario.domain.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

   Role findByNome(String name);

}
