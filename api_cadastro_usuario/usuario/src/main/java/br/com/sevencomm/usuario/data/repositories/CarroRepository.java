package br.com.sevencomm.usuario.data.repositories;
//buscar dados no banco de dados

import br.com.sevencomm.usuario.domain.models.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByUser_Id(Long userId); //BY-> where, traga tudo do meu User de acordo com o userId informado

}
