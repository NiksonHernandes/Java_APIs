package br.com.sevencomm.treino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TreinoApplication {

	public static void main(String[] args) {

		SpringApplication.run(TreinoApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("123"));

	}

}
