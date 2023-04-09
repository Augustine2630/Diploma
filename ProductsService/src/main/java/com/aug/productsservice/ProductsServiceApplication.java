package com.aug.productsservice;

import com.aug.productsservice.service.PizzeriaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductsServiceApplication {

	private final PizzeriaService pizzeriaService;

	public ProductsServiceApplication(PizzeriaService pizzeriaService) {
		this.pizzeriaService = pizzeriaService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductsServiceApplication.class, args);
	}


}
