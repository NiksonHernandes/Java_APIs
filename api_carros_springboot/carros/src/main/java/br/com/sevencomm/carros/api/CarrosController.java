package br.com.sevencomm.carros.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sevencomm.carros.domain.Carro;
import br.com.sevencomm.carros.domain.CarroService;
import br.com.sevencomm.carros.domain.dto.CarroDTO;

@RestController 
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	//private CarroService service = new CarroService(); //instancia(objeto)privada da classe CarroService que vai buscar os carros
	
	@Autowired //explicado no CarroService, injeta essa dependencia CarroService e cria um obj do tipo CarroService
	private CarroService service;

	//----------CRIAÇÃO DA API SEM OS METODOS HTTPS--------------
	
	/*@GetMapping()
	public Iterable<Carro> get() { 
		return service.getCarros();
	}*/
	
	/*@GetMapping("/{id}")
	public Optional<Carro> get(@PathVariable("id") Long id) {
		return service.getCarroById(id);//retorna apenas o carro e não a lista
	}*/
	
	/*
	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo) { //retorna Iterable pq é uma lista
		return service.getCarroByTipo(tipo);//retorna apenas o carro e não a lista
	}
	
	//POST
	@PostMapping
	public String post(@RequestBody Carro carro) {
		//@requestBody -> converte o JSON do Carro para o objeto carro, dentro do corpo da requisição
		Carro c = service.insert(carro);
		//to guardando o retorno na variável c da classe Carro, por isso posso usar os métodos dela 
		
		return "Carro salvo com sucesso " + c.getId();
	}
	
	//PUT - ALTERAR
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro c = service.update(carro, id);
		
		return "Carro Atualziado com sucesso " + c.getId();
	}
	
	//delete
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		 service.delete(id);
		
		return "Carro deletado com sucesso ";
	}
	*/
	//----------CRIAÇÃO DA API COM OS MÉTODOS HTTPS--------------
	
	
	//get
	@GetMapping()
	public ResponseEntity<List<CarroDTO>> get() { 
		//ResponseEntity do Spring ele é tipado e recebe o tipo, no caso uma lista de carros
		
		//--> return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
		//retorna o response que recebe o obj que será retornado como JSON e o segundo argumento é o código
		//de retorno do protocolo HTTP, o OK é o nosso 200
		
		//-----------JEITO MAIS SIMPLIFICADO-----------:
		return ResponseEntity.ok(service.getCarros());
	}
	
	//pegar pelo id
	@GetMapping("/{id}")
	public ResponseEntity<CarroDTO> get(@PathVariable("id") Long id) {
		Optional<CarroDTO> carro = service.getCarroById(id);
		if(carro.isPresent()) {
			CarroDTO c = carro.get(); //converte carro, para CarroDTO
			return ResponseEntity.ok(c);
		}else {
			return ResponseEntity.notFound().build();
		}
//		return carro
//				.map(ResponseEntity::ok)
//				.orElse(ResponseEntity.notFound().build());
		
	}
	
	//pelo tipo
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<CarroDTO>> getCarrosByTipo(@PathVariable("tipo") String tipo) { 
		//retorna Iterable pq é uma lista
		List<CarroDTO> carros = service.getCarroByTipo(tipo);
		//retorna apenas o carro e não a lista
		
		return carros.isEmpty() ? 
				ResponseEntity.noContent().build() : //no contem
				ResponseEntity.ok(carros);//o 200
	}
	
	@PostMapping
	@Secured({"ROLE_ADMIN"}) //somente o perfil de adm pode fazer post
	public ResponseEntity<CarroDTO> post(@RequestBody Carro carro) {
		//@requestBody -> converte o JSON do Carro para o objeto carro, dentro do corpo da requisição
//		Carro c = service.insert(carro);
//		//to guardando o retorno na variável c da classe Carro, por isso posso usar os métodos dela 
//		
//		return "Carro salvo com sucesso " + c.getId();
		try {
			CarroDTO c = service.insert(carro);
			
			URI location = getUri(c.getId());
			return ResponseEntity.created(location).build();
		}catch(Exception ex) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")//monta a URL
				.buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity<CarroDTO> put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		carro.setId(id);
		CarroDTO c = service.update(carro, id);
		
		return c != null ?
				ResponseEntity.ok(c) : 
				ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CarroDTO> delete(@PathVariable("id") Long id) {
		 boolean ok = service.delete(id);
		
		 return ok ?
				ResponseEntity.ok().build() : 
				ResponseEntity.notFound().build();
		
	}
	
}
