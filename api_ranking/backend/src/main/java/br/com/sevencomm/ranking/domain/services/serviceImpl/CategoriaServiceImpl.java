package br.com.sevencomm.ranking.domain.services.serviceImpl;

import br.com.sevencomm.ranking.data.repositories.CategoriaRepository;
import br.com.sevencomm.ranking.data.repositories.CategoriaUserRepository;
import br.com.sevencomm.ranking.data.repositories.ClubeRepository;
import br.com.sevencomm.ranking.data.repositories.UserRepository;
import br.com.sevencomm.ranking.domain.models.Categoria;
import br.com.sevencomm.ranking.domain.models.CategoriaUser;
import br.com.sevencomm.ranking.domain.models.Clube;
import br.com.sevencomm.ranking.domain.models.User;
import br.com.sevencomm.ranking.domain.models.dtos.CategoriaDTO;
import br.com.sevencomm.ranking.domain.models.dtos.CategoriaUserDTO;
import br.com.sevencomm.ranking.domain.models.dtos.UpdateCategoriaDTO;
import br.com.sevencomm.ranking.domain.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository _categoriaRepository;

    @Autowired
    private ClubeRepository _clubeRepository;

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private CategoriaUserRepository _categoriaUserRepository;

    //MÉTODOS DO ADMINISTRADOR
    @Override
    public Categoria create(Categoria obj) {
        return _categoriaRepository.save(obj);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Categoria> categoriaOptional = _categoriaRepository.findById(id);

        if (!(categoriaOptional.isPresent()))
            throw new IllegalArgumentException("Id inválido");

        _categoriaRepository.deleteById(id);
    }

    @Override
    public List<Categoria> list() {
        return _categoriaRepository.findAll();
    }

    @Override
    public Categoria read(Long id) {
        Optional<Categoria> categoriaOptional = _categoriaRepository.findById(id);

        if (!(categoriaOptional.isPresent()))
            throw new IllegalArgumentException("Id inválido");

        return categoriaOptional.get();
    }

    @Override
    public Categoria update(Categoria obj) {
        Optional<Categoria> categoriaOptional = _categoriaRepository.findById(obj.getId());

        if (!(categoriaOptional.isPresent()))
            throw new IllegalArgumentException("Id inválido");

        return _categoriaRepository.save(obj);
    }


    //----------------------------------------------------------------------------

    @Override
    public void addUser(CategoriaUserDTO categoriaUserDTO) {
        Optional<Categoria> categoriaOptional = _categoriaRepository.findById(categoriaUserDTO.getCategoriaId());
        Optional<User> userOptional = _userRepository.findById(categoriaUserDTO.getUserId());

        if (!categoriaOptional.isPresent())
            throw new IllegalArgumentException("Id da categoria inválido ou categoria não encontrada");

        if (!userOptional.isPresent())
            throw new IllegalArgumentException("Id do usuário inválido ou usuáro não encontrado");

        CategoriaUser categoriaUser = new CategoriaUser();
        categoriaUser.setPontos(categoriaUserDTO.getPontos());
        categoriaUser.setPosicao(categoriaUserDTO.getPosicao());
        categoriaUser.setUser(userOptional.get());
        categoriaUser.setCategoria(categoriaOptional.get());

        _categoriaUserRepository.save(categoriaUser);
    }

    @Override
    @Transactional
    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getNome().isEmpty())
            throw new IllegalArgumentException("Campos vazios");

        Optional<Clube> clubeOptional = _clubeRepository.findById(categoriaDTO.getClubeId());

        if (!clubeOptional.isPresent())
            throw new IllegalArgumentException("Id do clube não encontrado");

        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        categoria.setClube(clubeOptional.get());

        _categoriaRepository.save(categoria);

        return CategoriaDTO.toDTO(categoria);
    }

    @Override
    @Transactional
    public void deleteCategoria(Long categoriaId) {
        Optional<Categoria> categoriaOptional = _categoriaRepository.findById(categoriaId);

        if (!(categoriaOptional.isPresent()))
            throw new IllegalArgumentException("Id para buscar inválido");

        Categoria categoria = categoriaOptional.get();

        _categoriaRepository.delete(categoria);
    }

    @Override
    public List<CategoriaUserDTO> listByPosicao() {
        List<CategoriaUser> categoriaUserList = _categoriaUserRepository.findByOrderByPosicaoAsc();

        if (categoriaUserList.isEmpty())
            throw new IllegalArgumentException("Lista Vazia!");

        return CategoriaUserDTO.toListDTO(categoriaUserList);
    }

    @Override
    public List<CategoriaDTO> listCategoria() {
        List<Categoria> categoriaList =_categoriaRepository.findAll();

        if (categoriaList.isEmpty())
            throw new IllegalArgumentException("Lista Vazia!");

        List<CategoriaDTO> categoriaDTOList = new ArrayList<>();

        categoriaDTOList = categoriaList.stream()
                .map((c) -> CategoriaDTO.toDTO(c))
                .collect(Collectors.toList());

        return categoriaDTOList;
    }

    @Override
    public List<CategoriaUserDTO> listCategoriaByCategoriaId(Long categoriaId) {
        List<CategoriaUser> categoriaUsers = _categoriaUserRepository.findByCategoria_IdOrderByPosicaoAsc(categoriaId);

        if (categoriaUsers.isEmpty())
            throw new IllegalArgumentException("Essa categoria não existe.");


        return CategoriaUserDTO.toListDTO(categoriaUsers);
    }

    @Override
    public CategoriaDTO readCategoria(Long categoriaId) {
        Optional<Categoria> categoriaOptional = _categoriaRepository.findById(categoriaId);

        if (!categoriaOptional.isPresent())
            throw new IllegalArgumentException("Essa categoria não existe.");

        return CategoriaDTO.toDTO(categoriaOptional.get());
    }

    @Override
    public void removeUser(Long userId, Long categoriaId) {
        Optional<Categoria> categoriaOptional = _categoriaRepository.findById(categoriaId);
        Optional<User> userOptional = _userRepository.findById(userId);

        if (!categoriaOptional.isPresent())
            throw new IllegalArgumentException("Id da categoria inválido ou categoria não encontrada");

        if (!userOptional.isPresent())
            throw new IllegalArgumentException("Id do usuário inválido ou usuáro não encontrado");

        Optional<CategoriaUser> categoriaUserOptional = _categoriaUserRepository.findByUser_IdAndCategoria_Id(userId, categoriaId);

        if (!categoriaUserOptional.isPresent())
            throw new IllegalArgumentException("Usuário não pertence a esta categoria.");

        _categoriaUserRepository.delete(categoriaUserOptional.get());
    }

    @Override
    public CategoriaDTO updateCategoria(UpdateCategoriaDTO updateCategoriaDTO) {
        Optional<Categoria> categoriaOptional = _categoriaRepository.findById(updateCategoriaDTO.getCategoriaId());

        if (updateCategoriaDTO.getNome().isEmpty())
            throw new IllegalArgumentException("Campos vazios");

        if (!(categoriaOptional.isPresent()))
            throw new IllegalArgumentException("Id para buscar inválido");

        Categoria categoria = categoriaOptional.get();

        categoria.setNome(updateCategoriaDTO.getNome());

        categoria = _categoriaRepository.save(categoria);

        return CategoriaDTO.toDTO(categoria);
    }

}
