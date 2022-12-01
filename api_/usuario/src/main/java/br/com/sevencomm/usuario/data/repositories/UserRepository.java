package br.com.sevencomm.usuario.data.repositories;

import br.com.sevencomm.usuario.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

}
