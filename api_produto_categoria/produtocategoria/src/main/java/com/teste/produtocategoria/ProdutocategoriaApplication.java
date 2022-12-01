package com.teste.produtocategoria;

import com.teste.produtocategoria.entities.Category;
import com.teste.produtocategoria.entities.Product;
import com.teste.produtocategoria.repositories.CategoryRespository;
import com.teste.produtocategoria.repositories.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ProdutocategoriaApplication implements CommandLineRunner {

	@Autowired
	private CategoryRespository categoryRespository;

	@Autowired
	private ProductRespository productRespository;

	public static void main(String[] args) {

		SpringApplication.run(ProdutocategoriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//tudo que for implementado aqui será iniciado junto
//		Category cat1 = new Category(1L, "Electronics");
//		Category cat2 = new Category(2L, "Books");
//
//		Product p1 = new Product(1L, "TV", 2200.00, cat1);
//		Product p2 = new Product(2L, "Domain Driven Design", 120.00, cat2);
//		Product p3 = new Product(3L, "PS5", 2800.00, cat1);
//		Product p4 = new Product(4L, "Docker", 100.00, cat2);

		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");

		Product p1 = new Product(null, "TV", 2200.00, cat1);
		Product p2 = new Product(null, "Domain Driven Design", 120.00, cat2);
		Product p3 = new Product(null, "PS5", 2800.00, cat1);
		Product p4 = new Product(null, "Docker", 100.00, cat2);

		cat1.getProducts().addAll(Arrays.asList(p1, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		//acessa a lista de associação da minha category e chama o método addALL, que add vários elementos ao msm tempo
		//Array.asList -> já cria uma lista


		//salvar dentro do map
		categoryRespository.save(cat1);
		categoryRespository.save(cat2);

		//salva os produtos
		productRespository.save(p1);
		productRespository.save(p2);
		productRespository.save(p3);
		productRespository.save(p4);
	}
}
