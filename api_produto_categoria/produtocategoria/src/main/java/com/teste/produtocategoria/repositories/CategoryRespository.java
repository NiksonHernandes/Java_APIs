package com.teste.produtocategoria.repositories;

//vai no meu BD e busca oq eu quero

import com.teste.produtocategoria.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component //diz que poder usar o Autowired - injecao de dependencia em outras classes
@Repository
public interface CategoryRespository extends JpaRepository<Category, Long> { //tipo da entidade e tipo do ID

   /*
   IMPLEMENTAÇÃO MANUAL, SEM JPA E COM MAP(SEM BANCO DE DADOS)

   private Map<Long, Category> map = new HashMap<>();//map é uma interface, coleção de pares valores(chave a valor)
    // to dizendo que: "vou guardar uma coleção de pares Long e do tipo Category"

    public void save(Category obj){
        map.put(obj.getId(), obj); //chave a valor
    }

    public Category findById(Long id){
        return map.get(id); //retorna o OBJ de acordo com a chave informada
    }

    public List<Category> findAll(){
        return new ArrayList<Category>(map.values()); //pega todos os valores do tipo informado e o ArratList instancia uma nova lista
    }*/
}
