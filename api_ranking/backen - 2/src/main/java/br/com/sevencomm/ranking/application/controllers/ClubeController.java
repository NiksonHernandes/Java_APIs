package br.com.sevencomm.ranking.application.controllers;

import br.com.sevencomm.ranking.domain.models.Clube;
import br.com.sevencomm.ranking.domain.models.dtos.ClubeDTO;
import br.com.sevencomm.ranking.domain.models.dtos.UpdateClubeDTO;
import br.com.sevencomm.ranking.domain.services.ClubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/clube")
public class ClubeController {

    @Autowired
    private ClubeService _clubeService;

    //MÉTODOS DO ADMINISTRADOR
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Clube clube) {
        try{
            return ResponseEntity.ok(_clubeService.create(clube));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam(value = "id") Long id) {
        try {
            _clubeService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        try{
            return ResponseEntity.ok(_clubeService.list());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/read")
    public ResponseEntity<Object> read(@RequestParam(value = "id") Long id) {
        try {
            return ResponseEntity.ok(_clubeService.read(id));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/update")
    public  ResponseEntity<Object> update(@RequestBody Clube clube) {
        try {
            return ResponseEntity.ok(_clubeService.update(clube));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    //---------------------------------------------------------------------------------

    @PostMapping("/add-user")
    public ResponseEntity<Object> addUser(@RequestBody Map<String, Long> params) {
        try {
            _clubeService.addUser(params.get("userId"), params.get("clubeId"));
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/create-clube")
    public ResponseEntity<Object> createClube(@RequestBody ClubeDTO clubeDTO) {
        try {
            return ResponseEntity.ok(_clubeService.createClube(clubeDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete-clube")
    public ResponseEntity<Object> deleteClube(@RequestParam(value = "clubeId") Long clubeId) {
        try {
            _clubeService.deleteClube(clubeId);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list-clube")
    public ResponseEntity<Object> listClube() {
        try {
            return ResponseEntity.ok(_clubeService.listClube());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/read-clube")
    public ResponseEntity<Object> readClube(@RequestParam(value = "clubeId") Long clubeId) {
        try {
            return ResponseEntity.ok(_clubeService.readClube(clubeId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/remove-user")
    public ResponseEntity<Object> removeUser(@RequestBody Map<String, Long> params) {
        try {
            _clubeService.removeUser(params.get("userId"), params.get("clubeId"));
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/update-clube")
    public ResponseEntity<Object> updateClube(@RequestBody UpdateClubeDTO updateClubeDTO) {
        try {
            return ResponseEntity.ok(_clubeService.updateClube(updateClubeDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
