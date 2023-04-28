package com.example.couriersservice.controller;

import com.example.couriersservice.model.Orders;
import com.example.couriersservice.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courier")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Orders> getOrders(@RequestParam("pizzeria_name") String pizzeriaName){
        return orderService.getAllByPizzeriaName(pizzeriaName);
    }

    @GetMapping("/accept-order")
    public void acceptOrder(@RequestParam("order_id") Integer orderId, @RequestParam("courier") String courier){
        orderService.acceptOrder(orderId, courier);
    }

    @GetMapping("/cour-orders")
    public List<Orders> getAllByCourier(@RequestParam("courier") String courier){
        return orderService.getAllByCourier(courier);
    }
}
