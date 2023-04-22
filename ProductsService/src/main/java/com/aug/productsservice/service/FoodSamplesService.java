package com.aug.productsservice.service;

import com.aug.productsservice.model.FoodSamples;
import com.aug.productsservice.repository.FoodSampleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodSamplesService {

    private final FoodSampleRepository foodSampleRepository;

    public FoodSamplesService(FoodSampleRepository foodSampleRepository) {
        this.foodSampleRepository = foodSampleRepository;
    }

    public List<FoodSamples> getAllFoodSamples(){
        return foodSampleRepository.findAll();
    }

    public List<FoodSamples> getAllByPizzeriaName(String pizzeriaName){
        return foodSampleRepository.findAllByPizzeriaName(pizzeriaName);
    }
}
