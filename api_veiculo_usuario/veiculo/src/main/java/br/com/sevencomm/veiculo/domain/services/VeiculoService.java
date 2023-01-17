package br.com.sevencomm.veiculo.domain.services;

import br.com.sevencomm.veiculo.domain.models.Veiculo;
import br.com.sevencomm.veiculo.domain.models.dtos.VeiculoDTO;

import java.util.List;

public interface VeiculoService {

    Veiculo addVeiculoByCurrentUser(VeiculoDTO veiculoDTO);
    Veiculo deleteVeiculoByCurrentUser(Long veiculoId);
    Veiculo getVeiculoById(Long veiculoId);
    List<Veiculo> listAllVeiculo();
    List<Veiculo> listAllVeiculoByCurrentUser();
    Veiculo updateVeiculoByCurrentUser(Long veiculoId, VeiculoDTO veiculoDTO);

}
