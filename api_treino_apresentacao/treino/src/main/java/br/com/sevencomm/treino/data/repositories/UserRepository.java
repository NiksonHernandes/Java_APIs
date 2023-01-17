package br.com.sevencomm.treino.data.repositories;

import br.com.sevencomm.treino.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByLogin(String Login);

    User findByLogin(String Login);

}
