package br.com.sevencomm.veiculo.application.controllers;

import br.com.sevencomm.veiculo.domain.models.dtos.VeiculoDTO;
import br.com.sevencomm.veiculo.domain.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    VeiculoService _veiculoService;

    @PostMapping("/add-veiculo-by-current-user")
    public ResponseEntity<Object> addVeiculoByCurrentUser(@RequestBody VeiculoDTO veiculoDTO) {
        try {
            return ResponseEntity.ok(_veiculoService.addVeiculoByCurrentUser(veiculoDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<Object> listAllVeiculo() {
        try {
            return ResponseEntity.ok(_veiculoService.listAllVeiculo());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list-all-veiculo-by-current-user")
    public ResponseEntity<Object> listAllVeiculoByCurrentUser() {
        try {
            return ResponseEntity.ok(_veiculoService.listAllVeiculoByCurrentUser());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete-veiculo-by-current-user/{id}")
    public ResponseEntity<Object> deleteVeiculoByCurrentUser(@PathVariable(value = "id") Long veiculoId) {
        try {
            return ResponseEntity.ok(_veiculoService.deleteVeiculoByCurrentUser(veiculoId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/update-veiculo-by-current-user/{id}")
    public ResponseEntity<Object> updateVeiculoByCurrentUser(@PathVariable(value = "id") Long veiculoId,
                                                             @RequestBody VeiculoDTO veiculoDTO) {
        try {
            return ResponseEntity.ok(_veiculoService.updateVeiculoByCurrentUser(veiculoId, veiculoDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
