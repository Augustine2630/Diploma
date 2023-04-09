package com.aug.productsservice.repository;

import com.aug.productsservice.model.Pizzeria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzeriaRepository extends JpaRepository<Pizzeria, Long> {

    List<Pizzeria> findAllByPizzeriaName(String name);
}