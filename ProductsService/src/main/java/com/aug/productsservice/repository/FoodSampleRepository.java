package com.aug.productsservice.repository;

import com.aug.productsservice.model.FoodSamples;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodSampleRepository extends JpaRepository<FoodSamples, Long> {

    List<FoodSamples> findAllByPizzeriaName(String pizzeriaName);
}