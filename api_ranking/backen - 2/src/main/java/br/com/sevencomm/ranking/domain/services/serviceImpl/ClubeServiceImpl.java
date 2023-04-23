package br.com.sevencomm.ranking.domain.services.serviceImpl;

import br.com.sevencomm.ranking.data.repositories.CategoriaRepository;
import br.com.sevencomm.ranking.data.repositories.ClubeRepository;
import br.com.sevencomm.ranking.data.repositories.ClubeUserRepository;
import br.com.sevencomm.ranking.data.repositories.UserRepository;
import br.com.sevencomm.ranking.domain.models.Categoria;
import br.com.sevencomm.ranking.domain.models.Clube;
import br.com.sevencomm.ranking.domain.models.ClubeUser;
import br.com.sevencomm.ranking.domain.models.User;
import br.com.sevencomm.ranking.domain.models.dtos.*;
import br.com.sevencomm.ranking.domain.services.ClubeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClubeServiceImpl implements ClubeService {

    @Autowired
    private ClubeRepository _clubeRepository;

    @Autowired
    private ClubeUserRepository _clubeUserRepository;

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private CategoriaRepository _categoriaRepository;

    //MÉTODOS DO ADMINISTRADOR
    @Override
    public Clube create(Clube obj) {
        return _clubeRepository.save(obj);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Clube> clubeOptional = _clubeRepository.findById(id);

        if (!(clubeOptional.isPresent()))
            throw new IllegalArgumentException("Id inválido");

        _clubeRepository.deleteById(id);
    }

    @Override
    public List<Clube> list() {
        return _clubeRepository.findAll();
    }

    @Override
    public Clube read(Long id) {
        Optional<Clube> clubeOptional = _clubeRepository.findById(id);

        if (!(clubeOptional.isPresent()))
            throw new IllegalArgumentException("Id inválido");

        return clubeOptional.get();
    }

    @Override
    public Clube update(Clube obj) {
        Optional<Clube> clubeOptional = _clubeRepository.findById(obj.getId());

        if (!(clubeOptional.isPresent()))
            throw new IllegalArgumentException("Id inválido");

        return _clubeRepository.save(obj);
    }

    //----------------------------------------------------------------------------

    @Override
    @Transactional
    public void addUser(Long userId, Long clubeId) {
        Optional<User> userOptional = _userRepository.findById(userId);
        Optional<Clube> clubeOptional = _clubeRepository.findById(clubeId);

        if (!(userOptional.isPresent()))
            throw new IllegalArgumentException("Username incorreto");

        if (!(clubeOptional.isPresent()))
            throw new IllegalArgumentException("Clube incorreto");

        Optional<ClubeUser> clubeUserOptional = _clubeUserRepository.findByUser_IdAndClube_Id(userId, clubeId);

        if (clubeUserOptional.isPresent())
            throw new IllegalArgumentException("Usuário já é membro deste clube.");

        ClubeUser clubeUser = new ClubeUser();
        clubeUser.setUser(userOptional.get());
        clubeUser.setClube(clubeOptional.get());

        _clubeUserRepository.save(clubeUser);
    }

    @Override
    @Transactional
    public ClubeDTO createClube(ClubeDTO clubeDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();//pega o usuário autenticado

        if (clubeDTO.getNome() == null || clubeDTO.getNome().trim().equals(""))
            throw new IllegalArgumentException("Campos vazios");

        Clube clube = new Clube();
        clube.setNome(clubeDTO.getNome());
        clube.setOwnerUser(currentUser);

        clube = _clubeRepository.save(clube);

        //coloca o owner como membro
        ClubeUser clubeUser = new ClubeUser();
        clubeUser.setClube(clube);
        clubeUser.setUser(currentUser);

        _clubeUserRepository.save(clubeUser);

        return ClubeDTO.toDTO(clube);
    }

    @Override
    @Transactional
    public void deleteClube(Long clubeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();//pega o usuário autenticado

        Optional<Clube> optionalClube = _clubeRepository.findById(clubeId);

        if (!(optionalClube.isPresent()))
            throw new IllegalArgumentException("Clube inexistente!");

        Clube clube = optionalClube.get();

        if (clube.getOwnerUser().getId() != currentUser.getId())
            throw new IllegalArgumentException("Sem permissão de deletar este clube. Somente o dono do clube pode deleta-lo!");

        _clubeRepository.deleteById(clubeId);
    }

    @Override
    public List<ClubeListDTO> listClube() {
        List<Clube> clubeList = _clubeRepository.findAll();

        if (clubeList.isEmpty())
            throw new IllegalArgumentException("Lista Vazia!");

        List<ClubeListDTO> clubeListDTO = clubeList
                .stream()
                .map((c) -> {
                    return new ClubeListDTO(
                            c.getId(),
                            c.getNome(),
                            c.getOwnerUser().getNome_completo(),
                            c.getOwnerUser().getEmail()
                    );
                })
                .collect(Collectors.toList());

        return clubeListDTO;
    }

    @Override
    public ClubeReadDTO readClube(Long cludeId) {
        Optional<Clube> clubeList = _clubeRepository.findById(cludeId);

        if (!(clubeList.isPresent()))
            throw new IllegalArgumentException("Clube inexistente!");

        //pegar usuários do clube pelo id do clube
        List<ClubeUser> clubeUserList = _clubeUserRepository.findByClube_Id(clubeList.get().getId());

        //pegar a lista de categorias através do id do clube
        List<Categoria> categoriaList = _categoriaRepository.findByClube_Id(clubeList.get().getId());

        ClubeReadDTO clubeReadDTO =
                new ClubeReadDTO(
                        clubeList.get().getId(),
                        clubeList.get().getNome(),
                        clubeList.get().getOwnerUser().getNome_completo(),
                        clubeList.get().getOwnerUser().getEmail(),
                        UserDTO.toListDTO(clubeUserList),
                        CategoriaDTO.toListDTO(categoriaList)
                );

        return clubeReadDTO;

//        ClubeReadDTO clubeReadDTO =
//                new ClubeReadDTO(
//                        clubeList.get().getId(),
//                        clubeList.get().getNome(),
//                        clubeList.get().getOwner_user().getNome_completo(),
//                        clubeList.get().getOwner_user().getEmail(),
//                        clubeList.get().getClubeUsers().stream()
//                                .map(x -> {
//                                    return  new UserDTO(
//                                            x.getUser().getId(),
//                                            x.getUser().getUsername(),
//                                            x.getUser().getNome_completo(),
//                                            x.getUser().getEmail()
//                                    );
//                                })
//                                .collect(Collectors.toList()),
//
//                        clubeList.get().getCategorias().stream()
//                                .map(x -> {
//                                    return new CategoriaDTO(
//                                            x.getId(),
//                                            x.getNome(),
//                                            x.getClube().getId()
//                                    );
//                                }).collect(Collectors.toList())
//                );
//
//        return clubeReadDTO;
    }

    @Override
    @Transactional
    public void removeUser(Long userId, Long clubeId) {
        Optional<User> userOptional = _userRepository.findById(userId);
        Optional<Clube> clubeOptional = _clubeRepository.findById(clubeId);

        if (!(userOptional.isPresent()))
            throw new IllegalArgumentException("Username incorreto");

        if (!(clubeOptional.isPresent()))
            throw new IllegalArgumentException("Clube incorreto");

        Optional<ClubeUser> clubeUserOptional = _clubeUserRepository.findByUser_IdAndClube_Id(userId, clubeId);

        if (!clubeUserOptional.isPresent())
            throw new IllegalArgumentException("Usuário não encontrado nesse clube.");

        _clubeUserRepository.delete(clubeUserOptional.get());
    }

    @Override
    public UpdateClubeDTO updateClube(UpdateClubeDTO updateClubeDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();//pega o usuário autenticado

        Optional<Clube> optionalClube = _clubeRepository.findById(updateClubeDTO.getId());

        if (updateClubeDTO.getNome().equals(""))
            throw new IllegalArgumentException("Campos vazios");

        if (!(optionalClube.isPresent()))
            throw new IllegalArgumentException("Id do clube inválido");

        Clube clube = optionalClube.get();

        //verifica se ele é o dono do clube
        if (clube.getOwnerUser().getId() != currentUser.getId())
            throw new IllegalArgumentException("Sem permissão para alterar este clube. Somente o dono do clube pode altera-lo!");

        clube.setNome(updateClubeDTO.getNome());

        _clubeRepository.save(clube);

        return UpdateClubeDTO.toDTO(clube);
    }

}




