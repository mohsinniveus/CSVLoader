package com.niveus.CSVLoader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niveus.CSVLoader.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
