package br.com.sevencomm.veiculo.domain.services.serviceImpl;

import br.com.sevencomm.veiculo.data.repositories.VeiculoRepository;
import br.com.sevencomm.veiculo.domain.models.Usuario;
import br.com.sevencomm.veiculo.domain.models.Veiculo;
import br.com.sevencomm.veiculo.domain.models.dtos.VeiculoDTO;
import br.com.sevencomm.veiculo.domain.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository _veiculoRepository;

    @Override
    @Transactional
    public Veiculo addVeiculoByCurrentUser(VeiculoDTO veiculoDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario currentUser = (Usuario) authentication.getPrincipal();//pega o usuário autenticado

        if (veiculoDTO.getNome().equals("") || veiculoDTO.getTipo().equals("")) {
            throw new IllegalArgumentException("Campos vazios");
        }

        Veiculo _veiculo = new Veiculo();
        _veiculo.setNome(veiculoDTO.getNome());
        _veiculo.setTipo(veiculoDTO.getTipo());
        _veiculo.setUsuario(currentUser);

        _veiculo = _veiculoRepository.save(_veiculo);

        return _veiculo;
    }

    @Override
    public List<Veiculo> listAllVeiculo() {
        List<Veiculo> veiculoList = _veiculoRepository.findAll();

        if (veiculoList.isEmpty()) {
            throw new IllegalArgumentException("Lista Vazia!");
        }

        return veiculoList;
    }

    @Override
    public List<Veiculo> listAllVeiculoByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario currentUser = (Usuario) authentication.getPrincipal();//pega o usuário autenticado

        List<Veiculo> veiculoList = _veiculoRepository.findByUsuario_Id(currentUser.getId());

        if (veiculoList.isEmpty()) {
            throw new IllegalArgumentException("Lista Vazia!");
        }

        return veiculoList;
    }

    @Override
    @Transactional
    public Veiculo deleteVeiculoByCurrentUser(Long veiculoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario currentUser = (Usuario) authentication.getPrincipal();//pega o usuário autenticado

        Optional<Veiculo> optionalVeiculo = _veiculoRepository.findById(veiculoId);

        if (!(optionalVeiculo.isPresent())) {
            throw new IllegalArgumentException("Id do veículo inválido");
        }

        Veiculo _veiculo = optionalVeiculo.get();

        if (_veiculo.getUsuario().getId() != currentUser.getId()) {
            throw new IllegalArgumentException("Sem permissão de deletar este carro");
        }

        _veiculoRepository.deleteById(veiculoId);

        return _veiculo;
    }

    @Override
    public Veiculo getVeiculoById(Long veiculoId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario currentUser = (Usuario) authentication.getPrincipal();//pega o usuário autenticado

        Optional<Veiculo> optionalVeiculo = _veiculoRepository.findById(veiculoId); //pega os dados do veiculo pelo ID

        if (!(optionalVeiculo.isPresent())) {//verificar se o id existe

            throw new IllegalArgumentException("Id do veículo inválido");
        }

        Veiculo _veiculo = optionalVeiculo.get();

        if (_veiculo.getUsuario().getId() != currentUser.getId()) { //verifica se o usuário pode acessar esse veiculo
            throw new IllegalArgumentException("Sem permissão para acessar este carro");
        }

        return _veiculo;
    }

    @Override
    public Veiculo updateVeiculoByCurrentUser(Long veiculoId, VeiculoDTO veiculoDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario currentUser = (Usuario) authentication.getPrincipal();//pega o usuário autenticado

        if (veiculoDTO.getNome().equals("") || veiculoDTO.getTipo().equals("")) {
            throw new IllegalArgumentException("Campos vazios");
        }

        Optional<Veiculo> optionalVeiculo = _veiculoRepository.findById(veiculoId);

        if (!(optionalVeiculo.isPresent())) {
            throw new IllegalArgumentException("Id do veículo inválido");
        }

        Veiculo _veiculo = optionalVeiculo.get(); //pega o Id armazenado no Optional

        if (_veiculo.getUsuario().getId() != currentUser.getId()) {
            throw new IllegalArgumentException("Sem permissão para alterar este carro");
        }

        _veiculo.setNome(veiculoDTO.getNome());
        _veiculo.setTipo(veiculoDTO.getTipo());

        _veiculo = _veiculoRepository.save(_veiculo);

        return _veiculo;
    }

}
