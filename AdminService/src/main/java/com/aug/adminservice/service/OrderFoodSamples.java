package com.aug.adminservice.service;


import com.aug.adminservice.repository.FoodSampleRepository;
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
