package br.com.sevencomm.usuario.application.controllers;

import br.com.sevencomm.usuario.domain.models.dtos.CarroDTO;
import br.com.sevencomm.usuario.domain.services.CarroService;
import br.com.sevencomm.usuario.domain.services.serviceImpl.CarroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService _carroService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/add-carro")
    public ResponseEntity<Object> addCarro(@RequestBody @Valid CarroDTO carroDTO) {
        try {
            return ResponseEntity.ok(_carroService.addCarro(carroDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/add-carro-by-current-user")
    public ResponseEntity<Object> addCarroByCurrentUser(@RequestBody @Valid CarroDTO carroDTO) {
        try {
            return ResponseEntity.ok(_carroService.addCarroByCurrentUser(carroDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> listAllCarro() {
        try {
            return ResponseEntity.ok(_carroService.listAllCarro());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/get-carro-by-id/{id}")
    public ResponseEntity<Object> getCarroById(@PathVariable(value = "id") Long carroId) {
        try {
            return ResponseEntity.ok(_carroService.getCarroById(carroId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list-carro-by-current-user")
    public ResponseEntity<Object> listCarroByCurrentUser() {
        try {
            return ResponseEntity.ok(_carroService.listCarroByCurrentUser());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/delete-carro/{id}")
    public ResponseEntity<Object> deleteCarroById(@PathVariable(value = "id") Long carroId) {
        try {
            return ResponseEntity.ok(_carroService.deleteCarroById(carroId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete-carro-by-current-user/{id}")
    public ResponseEntity<Object> deleteCarroByCurrentUser(@PathVariable(value = "id") Long carroId) {
        try {
            return ResponseEntity.ok(_carroService.deleteCarroByCurrentUser(carroId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/update-carro/{id}")
    public  ResponseEntity<Object> updateCarroById(@PathVariable(value = "id") Long carroId,
                                             @RequestBody @Valid CarroDTO carroDTO) {
        try {
            return ResponseEntity.ok(_carroService.updateCarroById(carroId, carroDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/update-carro-by-current-user/{id}")
    public  ResponseEntity<Object> updateCarroByCurrentUser(@PathVariable(value = "id") Long carroId,
                                                   @RequestBody @Valid CarroDTO carroDTO) {
        try {
            return ResponseEntity.ok(_carroService.updateCarroByCurrentUser(carroId, carroDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}

/*Carro carro = new Carro();
BeanUtils.copyProperties(carroDTO, carro);-> CONVERTE DE DTO PARA MODEL*/