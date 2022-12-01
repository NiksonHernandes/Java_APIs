package com.teste.produtocategoria.resources;

import com.teste.produtocategoria.entities.Category;
import com.teste.produtocategoria.repositories.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryRespository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){//responseEntity - encapsula uma resposta http
        //dentro do <Generic> vc vai ter q dizer o tipo dessa resposta, se vai ser lista, obj inteiro

        List<Category> list = categoryRepository.findAll();

        return ResponseEntity.ok().body(list);//retorna uma resposta bonitinha
        //.ok() -> responde se der tudo certo 200
        //.body() -> coloca o obj que vai ser o corpo da resposta
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category cat = categoryRepository.findById(id).get();//obtem o obj que ta dentro do optional
        return ResponseEntity.ok().body(cat);
    }
}
