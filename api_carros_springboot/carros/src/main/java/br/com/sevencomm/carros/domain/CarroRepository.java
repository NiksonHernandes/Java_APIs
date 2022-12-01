package br.com.sevencomm.carros.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

//interface -> colocamos uma interface e n uma classe, pq é na interface que ficam os serviços fornecidos pelo meu componente(bd)
public interface CarroRepository extends JpaRepository<Carro, Long> { //troca o CrudRepository para JpaRepository, é um filho do crud, tem as msms coisas
//CrudRespository é do Spring Boot
//Crud - Generics <Obj que estamos usando, Tipo do ID dele>
	
	//método criado automaticamente para procurar o tipo do meu carro
	List<Carro> findByTipo(String tipo); 

}

//JpaRepository -> o bom que ele retorna uma lista do findALL(), diferente do Crud que retorna um Iterable de findALL()