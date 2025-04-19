package com.tutorial.sovan92.repositories;

import com.tutorial.sovan92.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {}
