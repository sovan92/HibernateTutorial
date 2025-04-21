package com.tutorial.sovan92;

import com.tutorial.sovan92.entities.*;
import com.tutorial.sovan92.repositories.*;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class Sovan92HibernateTutorialApplicationTests {


	@Autowired
	ProductRepository productRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	FruitRepository fruitRepository;

	@Autowired
	HospitalRepository hospitalRepository;

	@Autowired
	CustomerRepository customerRepsitory;

	@Test
	void contextLoads() {
	}



	@Test
	void testProductSave(){

		Product product =
				Product.builder()
						.id(2)
						.name("beta")
						.desc("Awesome")
						.price(100d)
						.build();

		productRepository.save(product);

	}


	@Test
	void  testProductUpdate(){


		Product product =
				Product.builder()
						.id(1)
						.name("beta")
						.desc("Awesome")
						.price(100d)
						.build();
		productRepository.save(product);


		product = productRepository.findById(1).get();
		product.setName("beta");
		product.setPrice(200d);
		productRepository.save(product);

	}

	@Test
	void testProductDelete(){

		Product product =
				Product.builder()
						.id(3)
						.name("beta")
						.desc("Awesome")
						.price(500d)
						.build();
		productRepository.save(product);

		Optional<Product> productOptional = productRepository.findById(3);
		assert productOptional.isPresent();
		assertEquals(productOptional.get().getId(), 3, "Product Id is not correct");

		productRepository.deleteById(3);
		assertThrows(NoSuchElementException.class , ()->productRepository.findById(3).get());

	}

	@Test
	void testCount(){

		assertEquals(2, productRepository.count());

	}


	@Test
	void testCreateEmployee(){

		Employee employee =
				Employee.builder()
						.name("beta")
						.email("<EMAIL>")
						.build();
		employeeRepository.save(employee);

		Employee employee2 =
				Employee.builder()
						.name("theta")
						.email("rhyme")
						.build();
		employeeRepository.save(employee2);

	}

	@Test
	void testCreateFruit(){

		// Generation strategy identity increases the counter of id by one.
		Fruit fruit = Fruit.builder().name("apple").build();

		fruitRepository.save(fruit);



		Iterable<Fruit> fruitOptional = fruitRepository.findAll();
		fruitOptional.forEach(fruit1 -> fruitRepository.delete(fruit1));

	}

	@Test
	void testHospitalCreate(){

		Hospital hospital = Hospital.builder().name("Cama").build();
		hospitalRepository.save(hospital);

		Iterable<Hospital> hospitalIterable = hospitalRepository.findAll();
		hospitalIterable.forEach(hospital1 -> hospitalRepository.delete(hospital1));

	}

	@Test
	void testCustomStepIdGenerator(){

		Customer customer = Customer.builder().name("Name").email("name.alpha@gmail").build();
		customerRepsitory.save(customer);

	}




}
