package com.aug.productsservice.controller;

import com.aug.productsservice.POJO.OrderRequest;
import com.aug.productsservice.service.OrderFoodSamples;
import com.aug.productsservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderFoodSamples orderFoodSamples;
    private final OrderService ordersService;

    public OrderController(OrderFoodSamples orderFoodSamples, OrderService ordersService) {
        this.orderFoodSamples = orderFoodSamples;
        this.ordersService = ordersService;
    }

    @PostMapping("/order-basket")
    public ResponseEntity<HttpStatus> reduceFoodCount(@RequestBody OrderRequest[] orderRequest, @RequestParam("user_id") String userId){
        if ((orderRequest == null) || orderRequest.length == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        AtomicReference<String> address = new AtomicReference<>("");
        AtomicReference<String> pizzeriaName = new AtomicReference<>("");
        Arrays.stream(orderRequest).forEach(t -> {
            System.out.println(t);
            address.set(t.getAddress());
            pizzeriaName.set(t.getPizzeriaAddress());
            if(!(t.getFoodCount() == 0)){
                ordersService.addNewComposition(t.getFoodCount(), t.getFoodName());
            }
        });
        System.out.println("userId - " + userId);
        ordersService.addNewOrder(address.get(), dtf.format(now), "Card", "IN PROGRESS", pizzeriaName.get(), userId);


        return new ResponseEntity<>(HttpStatus.OK);
    }


    public void given(Map<String, Integer> orders) {
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Task performed on: " + new Date() + "n" +
                        "Thread's name: " + Thread.currentThread().getName());
                orders.forEach(orderFoodSamples::reduceSampleCount);
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 600000;
        timer.schedule(task, delay);
    }
}
