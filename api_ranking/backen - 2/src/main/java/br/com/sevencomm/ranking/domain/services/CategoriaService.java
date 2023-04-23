package br.com.sevencomm.ranking.domain.services;

import br.com.sevencomm.ranking.domain.models.Categoria;
import br.com.sevencomm.ranking.domain.models.CategoriaUser;
import br.com.sevencomm.ranking.domain.models.User;
import br.com.sevencomm.ranking.domain.models.dtos.CategoriaDTO;
import br.com.sevencomm.ranking.domain.models.dtos.CategoriaUserDTO;
import br.com.sevencomm.ranking.domain.models.dtos.UpdateCategoriaDTO;

import java.util.List;

public interface CategoriaService {

    //MÉTODOS DO ADMINISTRADOR
    Categoria create(Categoria obj);
    void delete(Long id);
    List<Categoria> list();
    Categoria read(Long id);
    Categoria update(Categoria obj);

    void addUser(CategoriaUserDTO categoriaUserDTO);
    CategoriaDTO createCategoria(CategoriaDTO categoriaDTO);
    void deleteCategoria(Long categoriaId);
    List<CategoriaUserDTO> listByPosicao();
    List<CategoriaDTO> listCategoria();
    List<CategoriaUserDTO> listCategoriaByCategoriaId(Long categoriaId);
    CategoriaDTO readCategoria(Long categoriaId);
    void removeUser(Long userId, Long categoriaId);
    CategoriaDTO updateCategoria(UpdateCategoriaDTO updateCategoriaDTO);

}
