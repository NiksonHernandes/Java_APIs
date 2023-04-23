package br.com.sevencomm.ranking.data.repositories;

import br.com.sevencomm.ranking.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String username);
    User findByUsername(String username);

}
