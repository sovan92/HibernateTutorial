package com.tutorial.sovan92;

import com.tutorial.sovan92.entities.Product;
import com.tutorial.sovan92.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Sovan92HibernateTutorialApplicationTests {


	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testProductSave(){

		Product product =
				Product.builder()
						.id(1)
						.name("alpha")
						.desc("Awesome")
						.price(100d)
						.build();

		productRepository.save(product);

	}

}
