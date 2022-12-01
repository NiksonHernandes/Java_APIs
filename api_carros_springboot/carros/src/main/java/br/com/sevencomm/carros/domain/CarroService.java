package br.com.sevencomm.carros.domain;
//PARA LER OS CARROS DO BANCO DE DADOS

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import br.com.sevencomm.carros.domain.dto.CarroDTO;

@org.springframework.stereotype.Service
//transforma a classe CarroService em um BIM gerenciavel pelo Spring, para facilitar a config, injeção de dependencia
//ou seja, faz com que ela não precise ser instanciada, basta colocar @Autowired

public class CarroService {
	
	@Autowired //injeção de dependencia, n precisa fazer o New, nem ter uma classe, o Spring se vira
	private CarroRepository rep;

	/*public Iterable<Carro> getCarros(){ //retorna um Iterable, pq o meu CRUD retorna um Iterable pra ele
		return rep.findAll(); //método do CrudRepository do Spring -> retorna tudo 
		
	}*/
	
	public List<CarroDTO> getCarros(){ 
		List<Carro> carros = rep.findAll();
		
		//MANEIRA 1:
//		List<CarroDTO> list = new ArrayList<>();
//		for(Carro c : carros){
//			list.add(new CarroDTO(c));
//		}
		
		//MANERIA 2 - USANDO LAMBDA
		List<CarroDTO> list = carros.stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
		//stream é um método que te permite mapear essa lista, ou seja, consegue usar o MAP(percorrer todos os elementos dela)
		
		return list; 
		
	}
	
	//metodo iterar ID
	public Optional<CarroDTO> getCarroById(Long Id){
		
		//return rep.findById(Id).map(CarroDTO::new);
		return rep.findById(Id).map(CarroDTO::create);
		
		
//		//para converter isso para um DTO: return rep.findById(Id);//findById já existe no meu crud do SpringBoot
//		Optional<Carro> carros = rep.findById(Id);
//		if(carros.isPresent()) {
//			return Optional.of(new CarroDTO(carros.get()));//converte o CarroDTO para Optional
//		}else {
//			return null;
//		}

	}
	
	//metodo iterar tipo
	public List<CarroDTO> getCarroByTipo(String tipo){
		
		List<Carro> carros = rep.findByTipo(tipo);
		//List<CarroDTO> list = carros.stream().map(c -> new CarroDTO(c)).collect(Collectors.toList()); -> sem o Model Map
		List<CarroDTO> list = carros.stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList()); // -> com o Model Map
		return list;
	}
	
	//metodo salvar
	public CarroDTO insert(Carro carro) {		
		//return rep.save(carro); //método save já existe na CRUD do Spring
		return CarroDTO.create(rep.save(carro));
	}
	
	//metodo put para atualizar
	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registo"); //verifica se o ID existe e se não é nulo
		
		//Busca o carro no banco de dados
		//Optional<Carro> optional = getCarroById(id); //pega o carro no banco de dados
		Optional<Carro> optional = rep.findById(id);
		if(optional.isPresent()) { //verifica se ele existe
			Carro db = optional.get(); //isPresent()retornará true e get()retornará o valor
			//Copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(db.getTipo());
			System.out.println("Carro id: " + db.getId());
			
			//Atualiza o carro
			rep.save(db);
			
			return CarroDTO.create(db);
		}else {
			return null;
			//throw new RuntimeException("Não foi possível atualizar o registro");
		}
	}
	
	//metodo para deletar
	public boolean delete(Long id) {
		Optional<CarroDTO> carro = getCarroById(id); //OPTIONAL INDICA QUE O CARRO PODE EXISTIR OU NÃO, É MEIO Q UMA PROMISSE
		//Um objeto container que pode ou não conter um valor não nulo. Se um valor estiver presente, isPresent() retornará true.
		if(carro.isPresent()) {
			rep.deleteById(id); //metodo deleteById já é do spring
			return true;
		}else{
			//throw new RuntimeException("Não foi possível deletar o registro");
			return false;
		}
		
	}
	
	/*----------------------------------------------------------------------
	//método para retornar uma lista de carros (fora do bd, ainda em memória) 
	public List<Carro> getCarrosFake(){
		
		//List<Carro> carros; // list<generic> -> especifico o tipo de cada obj da minha lista
		List<Carro> carros = new ArrayList<Carro>();
		
		/*Carro carro1 = new Carro(1L, "Fuscsdasa");
		carros.add(carro1);
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Brasília"));
		carros.add(new Carro(3L, "Chevete"));
		
		return carros;
 	}*/

}
