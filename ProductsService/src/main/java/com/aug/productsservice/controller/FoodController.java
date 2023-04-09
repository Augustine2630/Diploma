package com.aug.productsservice.controller;

import com.aug.productsservice.model.Pizzeria;
import com.aug.productsservice.service.PizzeriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final PizzeriaService pizzeriaService;

    public FoodController(PizzeriaService pizzeriaService) {
        this.pizzeriaService = pizzeriaService;
    }

    @GetMapping("/all-food")
    public List<Pizzeria> getFoodByPizzeria(@RequestParam("pizzeria") String pizzeria){
        return pizzeriaService.getAllByPizzeriaName(pizzeria);
    }
}
