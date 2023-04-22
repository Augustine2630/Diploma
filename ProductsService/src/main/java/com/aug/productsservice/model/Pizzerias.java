package com.aug.productsservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pizzerias {

    @Id
    private Long id;

    @Column(name = "pizzeria_name")
    private String pizzeriaName;

    @Column(name = "pizzeria_address")
    private String pizzeriaAddress;

    public String getPizzeriaAddress() {
        return pizzeriaAddress;
    }

    public void setPizzeriaAddress(String pizzeriaAddress) {
        this.pizzeriaAddress = pizzeriaAddress;
    }

    public String getPizzeriaName() {
        return pizzeriaName;
    }

    public void setPizzeriaName(String pizzeriaName) {
        this.pizzeriaName = pizzeriaName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
