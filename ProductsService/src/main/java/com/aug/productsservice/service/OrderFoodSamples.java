package com.aug.productsservice.service;

import com.aug.productsservice.repository.FoodSampleRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderFoodSamples {

    private final FoodSampleRepository foodSampleRepository;

    public OrderFoodSamples(FoodSampleRepository foodSampleRepository) {
        this.foodSampleRepository = foodSampleRepository;
    }

    public void reduceSampleCount(String foodSample, Integer count){
        foodSampleRepository.updateFoodCount(foodSample, count);
    }
}
