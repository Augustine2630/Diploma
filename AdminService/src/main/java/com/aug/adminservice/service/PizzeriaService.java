package com.aug.adminservice.service;


import com.aug.adminservice.model.Pizzerias;
import com.aug.adminservice.repository.PizzeriaRepository;
import org.springframework.stereotype.Service;

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
