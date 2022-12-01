package br.com.sevencomm.usuario.domain.services.serviceImpl;

import br.com.sevencomm.usuario.data.repositories.CarroRepository;
import br.com.sevencomm.usuario.domain.models.Carro;
import br.com.sevencomm.usuario.domain.models.User;
import br.com.sevencomm.usuario.domain.models.dtos.CarroDTO;
import br.com.sevencomm.usuario.domain.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarroServiceImpl implements CarroService {

    @Autowired
    private CarroRepository _carroRepository;

    @Transactional
    public Carro addCarro(CarroDTO carroDTO) {

        if (carroDTO.getNome().equals("") || carroDTO.getTipo().equals("")) {
            throw new IllegalArgumentException("Campos vazios");
        }

        Carro carro = new Carro();
        carro.setNome(carroDTO.getNome());
        carro.setTipo(carroDTO.getTipo());

        carro = _carroRepository.save(carro);

        return carro;
    }

    @Transactional
    public Carro addCarroByCurrentUser(CarroDTO carroDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();//pega o usuário autenticado

        Carro _carro = new Carro();
        _carro.setNome(carroDTO.getNome());
        _carro.setTipo(carroDTO.getTipo());
        _carro.setUser(currentUser);

        _carro = _carroRepository.save(_carro);

        return _carro;
    }

    public List<Carro> listAllCarro() {
        List<Carro> carro = _carroRepository.findAll();

        if (carro.isEmpty()) {
            throw new IllegalArgumentException("Lista Vazia!");
        }

        return carro;
    }

    public List<Carro> listCarroByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();//pega o usuário já autenticado

        List<Carro> carroList = _carroRepository.findByUser_Id(currentUser.getId());

        return carroList;
    }

    public Carro getCarroById(Long carroId) {
        Optional<Carro> carroOptional = _carroRepository.findById(carroId);

        if (!carroOptional.isPresent()) {
            throw new IllegalArgumentException("Id para buscar inválido");
        }

        return carroOptional.get();
    }

    @Transactional
    public Carro deleteCarroById(Long carroId) {
        Optional<Carro> carroOptional = _carroRepository.findById(carroId);

        if (carroOptional.isPresent()) {
            Carro carro = carroOptional.get();
            _carroRepository.delete(carro);
            return carro;
        }

        throw new IllegalArgumentException("Id para deletar inválido");
    }

    @Transactional
    public Carro deleteCarroByCurrentUser(Long carroId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();//pega o usuário autenticado

        Optional<Carro> carroOptional = _carroRepository.findById(carroId);

        if (!(carroOptional.isPresent())) {
            throw new IllegalArgumentException("(ID) Carro não encontrado");
        }

        Carro carro = carroOptional.get(); //pega o Id armazenado no Optional

        if (carro.getUser().getId() != currentUser.getId()) {
            throw new IllegalArgumentException("Sem permissão de deletar este carro");
        }

        _carroRepository.deleteById(carroId);

        return carro;
    }

    public Carro updateCarroById(Long carroId, CarroDTO carroDTO) {
        if (carroDTO.getNome().equals("") || carroDTO.getTipo().equals("")) {
            throw new IllegalArgumentException("Campos vazios");
        }

        Carro _carro = getCarroById(carroId);
        _carro.setNome(carroDTO.getNome());
        _carro.setTipo(carroDTO.getTipo());

        _carro = _carroRepository.save(_carro);

        return _carro;
    }

    public Carro updateCarroByCurrentUser(Long carroId, CarroDTO carroDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();//pega o usuário autenticado

        if (carroDTO.getNome().equals("") || carroDTO.getTipo().equals("")) {
            throw new IllegalArgumentException("Campos vazios");
        }

        Optional<Carro> carroOptional = _carroRepository.findById(carroId);

        if (!(carroOptional.isPresent())) {
            throw new IllegalArgumentException("(ID) Carro não encontrado");
        }

        Carro carro = carroOptional.get(); //pega o Id armazenado no Optional

        if (carro.getUser().getId() != currentUser.getId()) {
            throw new IllegalArgumentException("Sem permissão para alterar este carro");
        }

        carro.setNome(carroDTO.getNome());
        carro.setTipo(carroDTO.getTipo());

        carro = _carroRepository.save(carro);

        return carro;
    }

}
