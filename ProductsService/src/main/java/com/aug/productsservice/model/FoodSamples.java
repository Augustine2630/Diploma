package com.aug.productsservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FoodSamples {

    @Id
    private Long id;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "food_count")
    private String foodCount;

    @Column(name = "pizzeria_name")
    private String pizzeriaName;

    public String getPizzeriaName() {
        return pizzeriaName;
    }

    public void setPizzeriaName(String pizzeriaName) {
        this.pizzeriaName = pizzeriaName;
    }

    public String getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(String foodCount) {
        this.foodCount = foodCount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
