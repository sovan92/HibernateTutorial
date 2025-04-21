package com.tutorial.sovan92.repositories;

import com.tutorial.sovan92.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> { }

