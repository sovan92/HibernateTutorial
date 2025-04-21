package com.tutorial.sovan92.repositories;

import com.tutorial.sovan92.entities.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {}
