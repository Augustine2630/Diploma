package com.aug.productsservice.controller;

import com.aug.productsservice.model.FoodSamples;
import com.aug.productsservice.service.FoodSamplesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodSamplesService foodSamplesService;

    public FoodController(FoodSamplesService foodSamplesService) {
        this.foodSamplesService = foodSamplesService;
    }

    @GetMapping("/all-food")
    public List<FoodSamples> getAllFoodSamples(){
        return foodSamplesService.getAllFoodSamples();
    }

    @GetMapping("/all-food-pz")
    public List<FoodSamples> getAllByPizzeriaName(@RequestParam("pizzeria") String pizzeriaName){
        return foodSamplesService.getAllByPizzeriaName(pizzeriaName);
    }
}
