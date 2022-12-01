package br.com.sevencomm.carros.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;


//DOMAIN VAI ARMAZENAR AS ENTIDADES

//CLASSE CARRO -> entidade

@Entity //mapeia a tabela Carro do bd dados aq, como o nome é o msm da tabela, sem problemas, mas se fosse diferente: 
//@Entity(name = "nome da tabela no banco de dados");
//@Getter @Setter gerado pelo LOMBOK ou podemos usar apenas @Data
@Data

public class Carro {
	
	@Id //indica que esse é o campo Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //incrementa o ID automaticamente
	private Long id;
	
	//se o nome fosse diferente da coluna no banco: @Column(name = "nome da coluna no banco")
	private String nome;
	private String tipo;
	
	
	/*	- SUBSTITUIDO PELO LOMBOK @Data
	public Carro() {
		//construtor padrão para que não de erro, pois o crud do Spring exige isso
	}
	
	//CONSTRUCTOR
	public Carro(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	//GETTERS AND SETTER 
	public Long getId() {
		return id;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}*/
}
