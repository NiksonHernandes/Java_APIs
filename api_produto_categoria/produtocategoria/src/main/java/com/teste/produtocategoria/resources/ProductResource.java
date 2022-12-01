package com.teste.produtocategoria.resources;

import com.teste.produtocategoria.entities.Product;
import com.teste.produtocategoria.repositories.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductRespository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){

        List<Product> list = productRepository.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product cat = productRepository.findById(id).get();
        return ResponseEntity.ok().body(cat);
    }
}
