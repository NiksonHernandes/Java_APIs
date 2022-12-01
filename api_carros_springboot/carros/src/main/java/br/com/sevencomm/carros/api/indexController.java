package br.com.sevencomm.carros.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController 
/*RestController -> essa notação que transforma essa classe em um web service rest*/
@RequestMapping("/")
/*coloca o mapeamento do rest service para o "/" como padrão, vai estar na raiz da aplicação*/

public class indexController {
	
	@GetMapping("/hello") 
	//GetMapping cria uma rota do webService 
	//é um atalho para o RequestMapping e o método GET, msm coisa que colocar @RequestMapping(method = RequestMethod.GET)
	//se uma requisição do tipo GET for chamada, ele chama esse método Hello
	public String hello() {
		return "Hello Spring Boot!";
	}
	
	//------------------TESTE PARA PEGAR AS CONFIGURAÇÕES DO USUÁRIO -----------------------
		@GetMapping("/userInfo")
		public UserDetails userInfo(@AuthenticationPrincipal UserDetails user) {
			return user;
		}

	//-------------------------------
	
	//se eu criar outro método, eu preciso mapear ele com um caminho diferente ("/teste"), quando não tem mapemento, ele herda da classe ("/")
	@GetMapping("/teste")
	public String teste() {
		return "Teste String BOOT!";
	}
	//--------------------------------
	
	@GetMapping()
	public String get() {//quando chamo a pagina eu chamo tmb esse método get 
		return "API dos carros!";
	}
	
	/*
	@GetMapping("/login")
	public String login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		/*@RequestParam('nome do parametro') ->  efetua a ligação do parametro com a variável
		 * NO URL: http://localhost:8080/login?login=ricardo&senha=abc 
		 * no primeiro sempre coloca: ? 
		 * para o resto colocar: &
		 *  
		return "Login: " + login + ", Senha: " + senha;
	}
	
	//-----------------------------------------------------------------
	
	//fazer do msm jeito, só que com '/'
	@GetMapping("/login/{login}/senha/{senha}")
	public String login(@PathVariable("login") String login, @PathVariable("senha") String senha) {
		
		/*@PathVariable faz a msm coisa que o RequestParam
		 * NO URL: http://localhost:8080/login/ricardo/senha/abc 
		return "Login: " + login + ", Senha: " + senha;
	}
	*/
	
	@GetMapping("/carros/{id}")
	public String getCarroById(@PathVariable("id") Long id) {
		return "Carro by ID: " + id;
	}
	
	@GetMapping("/carros/tipo/{tipo}")
	public String getCarroByTipo(@PathVariable("tipo") String tipo) {
		return "Lista de carros: " + tipo;
	}
	
	//Usando o POST em vez de GET para passar os parametros para o web service
//	@PostMapping("/login")
//	public String login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
//		//para testar vai no Postman -> Body -> x-www-form -> coloque o login na key e o valor no value
//		return "Login: " + login + " Senha: " + senha;
//	}

}
