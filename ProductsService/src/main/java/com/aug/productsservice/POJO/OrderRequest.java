package com.aug.productsservice.POJO;

public class OrderRequest {
    private String foodName;
    private Integer foodCount;

    private String address;

    private String pizzeriaAddress;

    private String orderPrice;

    public OrderRequest(String foodName, Integer foodCount, String address, String pizzeriaAddress, String orderPrice) {
        this.foodName = foodName;
        this.foodCount = foodCount;
        this.address = address;
        this.pizzeriaAddress = pizzeriaAddress;
        this.orderPrice = orderPrice;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getPizzeriaAddress() {
        return pizzeriaAddress;
    }

    public void setPizzeriaAddress(String pizzeriaAddress) {
        this.pizzeriaAddress = pizzeriaAddress;
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
                ", pizzeriaAddress='" + pizzeriaAddress + '\'' +
                '}';
    }
}
