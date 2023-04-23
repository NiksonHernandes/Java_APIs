package br.com.sevencomm.ranking.data.repositories;

import br.com.sevencomm.ranking.domain.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByClube_Id(long clubeId);

}
