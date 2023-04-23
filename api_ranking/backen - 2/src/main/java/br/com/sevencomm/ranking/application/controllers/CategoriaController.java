package br.com.sevencomm.ranking.application.controllers;

import br.com.sevencomm.ranking.domain.models.Categoria;
import br.com.sevencomm.ranking.domain.models.CategoriaUser;
import br.com.sevencomm.ranking.domain.models.User;
import br.com.sevencomm.ranking.domain.models.dtos.CategoriaDTO;
import br.com.sevencomm.ranking.domain.models.dtos.CategoriaUserDTO;
import br.com.sevencomm.ranking.domain.models.dtos.ClubeDTO;
import br.com.sevencomm.ranking.domain.models.dtos.UpdateCategoriaDTO;
import br.com.sevencomm.ranking.domain.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService _categoriaService;

    //MÉTODOS DO ADMINISTRADOR
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Categoria categoria) {
        try{
            return ResponseEntity.ok(_categoriaService.create(categoria));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam(value = "id") Long id) {
        try {
            _categoriaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        try{
            return ResponseEntity.ok(_categoriaService.list());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/read")
    public ResponseEntity<Object> read(@RequestParam(value = "id") Long id) {
        try {
            return ResponseEntity.ok(_categoriaService.read(id));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/update")
    public  ResponseEntity<Object> update(@RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok(_categoriaService.update(categoria));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    //---------------------------------------------------------------------------------

    @PostMapping("/add-user")
    public ResponseEntity<Object> addUser(@RequestBody CategoriaUserDTO categoriaUserDTO) {
        try {
            _categoriaService.addUser(categoriaUserDTO);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/create-categoria")
    public ResponseEntity<Object> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        try {
            return ResponseEntity.ok(_categoriaService.createCategoria(categoriaDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete-categoria")
    public ResponseEntity<Object> deleteCategoria(@RequestParam(value = "categoriaId") Long categoriaId) {
        try {
            _categoriaService.deleteCategoria(categoriaId);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list-posicao")
    public ResponseEntity<Object> listByPosicao() {
        try {
            return ResponseEntity.ok(_categoriaService.listByPosicao());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list-categoria")
    public ResponseEntity<Object> listCategoria() {
        try {
            return ResponseEntity.ok(_categoriaService.listCategoria());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list-categoria-user")
    public ResponseEntity<Object> listCategoriaByCategoriaId(@RequestParam(value = "categoriaId") Long categoriaId) {
        try {
            return ResponseEntity.ok().body(_categoriaService.listCategoriaByCategoriaId(categoriaId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/read-categoria")
    public ResponseEntity<Object> readCategoria(@RequestParam(value = "categoriaId") Long categoriaId) {
        try {
            return ResponseEntity.ok(_categoriaService.readCategoria(categoriaId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/remove-user")
    public ResponseEntity<Object> removeUser(@RequestBody Map<String, Long> params) {
        try {
            _categoriaService.removeUser(params.get("userId"), params.get("categoriaId"));
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/update-categoria")
    public ResponseEntity<Object> updateCategoria(@RequestBody UpdateCategoriaDTO updateCategoriaDTO) {
        try {
            return ResponseEntity.ok(_categoriaService.updateCategoria(updateCategoriaDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
