package br.com.sevencomm.veiculo.data.repositories;

import br.com.sevencomm.veiculo.domain.models.Veiculo;
import br.com.sevencomm.veiculo.domain.models.dtos.VeiculoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByUsuario_Id(Long usuarioId);

}
