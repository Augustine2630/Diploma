package com.aug.productsservice.service;

import com.aug.productsservice.model.Pizzerias;
import com.aug.productsservice.repository.PizzeriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzeriaService {

    private final PizzeriaRepository pizzeriaRepository;

    public PizzeriaService(PizzeriaRepository pizzeriaRepository) {
        this.pizzeriaRepository = pizzeriaRepository;
    }

    public List<Pizzerias> getAllPizzerias(){
        return pizzeriaRepository.findAll();
    }



}
