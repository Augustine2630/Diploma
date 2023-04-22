package com.aug.productsservice.repository;

import com.aug.productsservice.model.FoodSamples;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface FoodSampleRepository extends JpaRepository<FoodSamples, Long> {

    List<FoodSamples> findAllByPizzeriaName(String pizzeriaName);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE food_samples\n" +
            "SET food_count = food_count - ?2\n" +
            "WHERE food_name = ?1")
    void updateFoodCount(String foodSample, Integer count);
}