package com.example.couriersservice.controller;

import com.example.couriersservice.model.Orders;
import com.example.couriersservice.repository.OrdersRepository;
import com.example.couriersservice.repository.UserRepository;
import com.example.couriersservice.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/courier")
public class OrderController {

    private final OrderService orderService;
    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;

    public OrderController(OrderService orderService, OrdersRepository ordersRepository, UserRepository userRepository) {
        this.orderService = orderService;
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/orders")
    public List<Orders> getOrders(@RequestParam("pizzeria_name") String pizzeriaName){
        return orderService.getAllByPizzeriaName(pizzeriaName);
    }

    @GetMapping("/accept-order")
    public void acceptOrder(@RequestParam("order_id") String orderId, @RequestParam("courier") String courier, @RequestParam("id") Long courierId){
        orderService.acceptOrder(Integer.valueOf(orderId), courier);
        setOrderState(orderId, "DONE", courierId);
    }

    @GetMapping("/cour-orders")
    public List<Orders> getAllByCourier(@RequestParam("courier") String courier){
        return orderService.getAllByCourier(courier);
    }

    @GetMapping("/cour-now")
        public List<Orders> getActiveOrders(@RequestParam("courier") String courier, @RequestParam("status") String status){
        return orderService.getActive(courier, status);
    }

    public void setOrderState(String orderId, String status, Long courierId) {
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("set order to Done");
                System.out.println(orderId + " " + status);
                ordersRepository.setNewStatus(Integer.parseInt(orderId), status);
                userRepository.updateIsOnWork(false, courierId);
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 10000;
        timer.schedule(task, delay);
    }

}
