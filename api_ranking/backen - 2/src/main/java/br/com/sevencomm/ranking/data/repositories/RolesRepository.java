package br.com.sevencomm.ranking.data.repositories;

import br.com.sevencomm.ranking.domain.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByNome(String nome);
}
