package com.aug.productsservice.repository;

import com.aug.productsservice.model.FoodSamples;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodSampleRepository extends JpaRepository<FoodSamples, Long> {
}