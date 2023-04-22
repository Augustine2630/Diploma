package com.aug.adminservice.repository;


import com.aug.adminservice.model.Pizzerias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzeriaRepository extends JpaRepository<Pizzerias, Long> {

}