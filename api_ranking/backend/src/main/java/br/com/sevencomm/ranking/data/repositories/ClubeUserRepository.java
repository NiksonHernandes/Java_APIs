package br.com.sevencomm.ranking.data.repositories;

import br.com.sevencomm.ranking.domain.models.Clube;
import br.com.sevencomm.ranking.domain.models.ClubeUser;
import br.com.sevencomm.ranking.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubeUserRepository extends JpaRepository<ClubeUser, Long> {

    List<ClubeUser> findByClube_Id(Long clubeId); //busca o Id do meu clube direto no repository

    Optional<ClubeUser> findByUser_IdAndClube_Id(Long userId, Long clubeId);

}
