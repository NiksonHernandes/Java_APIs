package br.com.sevencomm.ranking.data.repositories;

import br.com.sevencomm.ranking.domain.models.Clube;
import br.com.sevencomm.ranking.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Long> {

    Boolean existsByNome(String nome);
    Clube findByNome(String nome);

}
