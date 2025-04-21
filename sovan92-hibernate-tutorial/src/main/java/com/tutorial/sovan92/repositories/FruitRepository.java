package com.tutorial.sovan92.repositories;

import com.tutorial.sovan92.entities.Fruit;
import org.springframework.data.repository.CrudRepository;

public interface FruitRepository extends CrudRepository<Fruit, Long> { }
