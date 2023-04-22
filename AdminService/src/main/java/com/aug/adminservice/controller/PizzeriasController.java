package com.aug.adminservice.controller;


import com.aug.adminservice.model.Pizzerias;
import com.aug.adminservice.service.PizzeriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizzeria")
public class PizzeriasController {

    private final PizzeriaService pizzeriaService;

    public PizzeriasController(PizzeriaService pizzeriaService) {
        this.pizzeriaService = pizzeriaService;
    }

    @GetMapping("/all-pizzerias")
    public List<Pizzerias> getAllPizzerias(){
        return pizzeriaService.getAllPizzerias();
    }
}
