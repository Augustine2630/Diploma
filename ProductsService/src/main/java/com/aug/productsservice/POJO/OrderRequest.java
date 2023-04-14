package com.aug.productsservice.POJO;

public class OrderRequest {
    private String foodName;
    private Integer foodCount;

    private String address;

    public OrderRequest(String foodName, Integer foodCount, String address) {
        this.foodName = foodName;
        this.foodCount = foodCount;
        this.address = address;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(Integer foodCount) {
        this.foodCount = foodCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "foodName='" + foodName + '\'' +
                ", foodCount=" + foodCount +
                ", address='" + address + '\'' +
                '}';
    }
}
