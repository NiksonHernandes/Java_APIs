package br.com.sevencomm.usuario.domain.services;
//Lista de serviços fornecidos pelo componente (CarroServiceImpl)

import br.com.sevencomm.usuario.domain.models.Carro;
import br.com.sevencomm.usuario.domain.models.dtos.CarroDTO;
import java.util.List;
import java.util.Optional;

public interface CarroService {

    Carro addCarro(CarroDTO carroDTO);
    Carro addCarroByCurrentUser(CarroDTO carroDTO);
    Carro deleteCarroByCurrentUser(Long carroId);
    Carro deleteCarroById(Long carroId);
    Carro getCarroById(Long carroId);
    List<Carro> listAllCarro();
    List<Carro> listCarroByCurrentUser();
    Carro updateCarroByCurrentUser(Long carroId, CarroDTO carroDTO);
    Carro updateCarroById(Long carroId, CarroDTO carroDTO);

}
