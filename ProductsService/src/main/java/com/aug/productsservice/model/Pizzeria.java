package com.aug.productsservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pizzeria")
public class Pizzeria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizzeria_id", nullable = false)
    private Long id;

    @Column(name = "pizzeria_name")
    private String pizzeriaName;

    @Column(name = "pizzeria_address")
    private String pizzeriaAddress;

    @OneToMany(mappedBy = "pizzeria", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<FoodSamples> foodSampleses = new ArrayList<>();

    public List<FoodSamples> getFoodSampleses() {
        return foodSampleses;
    }

    public void setFoodSampleses(List<FoodSamples> foodSampleses) {
        this.foodSampleses = foodSampleses;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPizzeriaName() {
        return pizzeriaName;
    }

    public void setPizzeriaName(String pizzeriaName) {
        this.pizzeriaName = pizzeriaName;
    }

    public String getPizzeriaAddress() {
        return pizzeriaAddress;
    }

    public void setPizzeriaAddress(String pizzeriaAddress) {
        this.pizzeriaAddress = pizzeriaAddress;
    }

}
