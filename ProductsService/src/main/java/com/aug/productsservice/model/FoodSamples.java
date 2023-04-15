package com.aug.productsservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class FoodSamples {

    @Id
    private Long id;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "food_count")
    private Integer foodCount;

    @Column(name = "pizzeria_name")
    private String pizzeriaName;

    private BigDecimal samplePrice;

    private String samplePrompt;

    public BigDecimal getSamplePrice() {
        return samplePrice;
    }

    public void setSamplePrice(BigDecimal samplePrice) {
        this.samplePrice = samplePrice;
    }

    public String getSamplePrompt() {
        return samplePrompt;
    }

    public void setSamplePrompt(String samplePrompt) {
        this.samplePrompt = samplePrompt;
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

    public Integer getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(Integer foodCount) {
        this.foodCount = foodCount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
