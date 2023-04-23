package br.com.sevencomm.ranking.domain.services;

import br.com.sevencomm.ranking.domain.models.Clube;
import br.com.sevencomm.ranking.domain.models.ClubeUser;
import br.com.sevencomm.ranking.domain.models.User;
import br.com.sevencomm.ranking.domain.models.dtos.*;

import java.util.List;

public interface ClubeService {

    //MÉTODOS DO ADMINISTRADOR
    Clube create(Clube obj);
    void delete(Long id);
    List<Clube> list();
    Clube read(Long id);
    Clube update(Clube obj);

    void addUser(Long userId, Long clubeId);
    ClubeDTO createClube(ClubeDTO clubeDTO);
    void deleteClube(Long clubeId);
    List<ClubeListDTO> listClube();
    ClubeReadDTO readClube(Long cludeId);
    void removeUser(Long userId, Long clubeId);
    UpdateClubeDTO updateClube(UpdateClubeDTO updateClubeDTO);

}
