package com.aug.productsservice.repository;

import com.aug.productsservice.model.Pizzerias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzeriaRepository extends JpaRepository<Pizzerias, Long> {

}