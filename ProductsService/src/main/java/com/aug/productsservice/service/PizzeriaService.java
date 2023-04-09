package com.aug.productsservice.service;

import com.aug.productsservice.model.Pizzeria;
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

    public List<String> getAllPizzerias(){
        List<String> pizzerias = new ArrayList<>();
        pizzeriaRepository.findAll().forEach(p -> {
            pizzerias.add(p.getPizzeriaName());
        });
        return pizzerias;
    }

    public List<Pizzeria> getAllByPizzeriaName(String name){
        return pizzeriaRepository.findAllByPizzeriaName(name);
    }
}
