package br.com.sevencomm.ranking.data.repositories;

import br.com.sevencomm.ranking.domain.models.CategoriaUser;
import br.com.sevencomm.ranking.domain.models.ClubeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaUserRepository extends JpaRepository<CategoriaUser, Long> {

    Optional<CategoriaUser> findByUser_IdAndCategoria_Id(Long userId, Long categoriaId);

    List<CategoriaUser> findByCategoria_IdOrderByPosicaoAsc(Long categoriaId);

    List<CategoriaUser> findByOrderByPosicaoAsc(); //retorna uma lista de posição ordenados

}
