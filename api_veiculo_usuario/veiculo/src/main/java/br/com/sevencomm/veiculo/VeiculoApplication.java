package br.com.sevencomm.veiculo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class VeiculoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeiculoApplication.class, args);

//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		String senhaCriptografada = bCryptPasswordEncoder.encode("123");
//		System.out.printf(senhaCriptografada);
	}

}
