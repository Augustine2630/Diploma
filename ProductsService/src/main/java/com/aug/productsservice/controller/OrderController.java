package com.aug.productsservice.controller;

import com.aug.productsservice.POJO.OrderRequest;
import com.aug.productsservice.model.OrderComposition;
import com.aug.productsservice.model.Orders;
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
    public ResponseEntity<HttpStatus> orderBasket(@RequestBody OrderRequest[] orderRequest, @RequestParam("user_id") String userId){
        if ((orderRequest == null) || orderRequest.length == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        AtomicReference<String> address = new AtomicReference<>("");
        AtomicReference<String> pizzeriaName = new AtomicReference<>("");
        Map<String, Integer> map = new HashMap<>();
        AtomicReference<String> orderPrice = new AtomicReference<>("0");
        Arrays.stream(orderRequest).forEach(t -> {
            map.put(t.getFoodName(), t.getFoodCount());
            address.set(t.getAddress());
            pizzeriaName.set(t.getPizzeriaAddress());
            orderPrice.set(t.getOrderPrice());
            if(!(t.getFoodCount() == 0)){
                ordersService.addNewComposition(t.getFoodCount(), t.getFoodName());
            }

        });
        reduceFood(map);

        ordersService.addNewOrder(address.get(), dtf.format(now), "Card", "IN PROGRESS", pizzeriaName.get(), userId, orderPrice.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user-orders")
    public List<Orders> getOrdersByUser(@RequestParam("user") String userId){
        return ordersService.getOrdersByUser(userId);
    }

    @GetMapping("/orders-comp")
    public List<OrderComposition> getOrdersComposition(@RequestParam("order_number") String orders){
        return ordersService.getOrdersCompositionByOrderNumber(orders);
    }

    @GetMapping("/orders-active")
    public List<Orders> getActiveOrders(@RequestParam("user") String user){
        return ordersService.getActiveOrders(user);
    }

    @GetMapping("/order-cancel")
    public void cancelOrder(@RequestParam("order_number") String orderNumber){
        ordersService.cancelOrder(orderNumber);
    }



    public void reduceFood(Map<String, Integer> orders) {
        orders.forEach(orderFoodSamples::reduceSampleCount);
    }




}
