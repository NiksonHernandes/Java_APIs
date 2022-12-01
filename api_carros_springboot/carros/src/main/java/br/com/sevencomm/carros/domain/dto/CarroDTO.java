package br.com.sevencomm.carros.domain.dto;

import org.modelmapper.ModelMapper;
import br.com.sevencomm.carros.domain.Carro;
import lombok.Data;

//DTO ->  é um padrão de projetos A ideia consiste basicamente em agrupar um conjunto de atributos numa classe simples de forma a otimizar a comunicação.
//Principalmente pra n retornar um JSON enorme de cada obj, faz retornar os dados resumidos com apenas os atributos q vc quer


@Data
public class CarroDTO {
	private Long id;
	private String nome;
	private String tipo;
	
//	public CarroDTO(Carro c) {//contrutor que recebe um carro
//		this.id = c.getId();
//		this.nome = c.getNome();
//		this.tipo = c.getTipo();
//		
//		//a ideia por trás disso é fazer com que o meu CarrosController não retorne mais uma lista de carros no método
//	}
	
	public static CarroDTO create(Carro c) { //do Model Map - substitui o New por Create
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarroDTO.class);
	}
}
