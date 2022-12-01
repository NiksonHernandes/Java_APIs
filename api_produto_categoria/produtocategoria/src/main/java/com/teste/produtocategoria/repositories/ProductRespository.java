package com.teste.produtocategoria.repositories;

//vai no meu BD e busca oq eu quero

import com.teste.produtocategoria.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component //diz que poder usar o Autowired - injecao de dependencia em outras classes
@Repository
public interface ProductRespository extends JpaRepository<Product, Long> {

    /*
    IMPLEMENTAÇÃO MANUAL, SEM JPA E COM MAP(SEM BANCO DE DADOS)
    private Map<Long, Product> map = new HashMap<>();

    public void save(Product obj){
        map.put(obj.getId(), obj); //chave a valor
    }

    public Product findById(Long id){
        return map.get(id); //retorna o OBJ de acordo com a chave informada
    }

    public List<Product> findAll(){
        return new ArrayList<Product>(map.values()); //pega todos os valores do tipo informado e o ArratList instancia uma nova lista
    }*/
}
