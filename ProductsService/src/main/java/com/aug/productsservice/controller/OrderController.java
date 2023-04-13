package com.aug.productsservice.controller;

import com.aug.productsservice.service.OrderFoodSamples;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderFoodSamples orderFoodSamples;

    public OrderController(OrderFoodSamples orderFoodSamples) {
        this.orderFoodSamples = orderFoodSamples;
    }

    @GetMapping("/order-basket")
    public HttpStatus reduceFoodCount(@RequestParam("foodName") String foodName, @RequestParam("count") Integer count){
        orderFoodSamples.reduceSampleCount(foodName, count);
        return HttpStatus.OK;
    }
}
