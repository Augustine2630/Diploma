package com.aug.productsservice.service;

import com.aug.productsservice.model.OrderComposition;
import com.aug.productsservice.model.Orders;
import com.aug.productsservice.repository.OrderCompositionRepository;
import com.aug.productsservice.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final OrderCompositionRepository orderCompositionRepository;
    public OrderService(OrdersRepository ordersRepository, OrderCompositionRepository orderCompositionRepository) {
        this.ordersRepository = ordersRepository;
        this.orderCompositionRepository = orderCompositionRepository;
    }
    List<Orders> orders = new ArrayList<>();

    public List<Orders> getAll(){
        return ordersRepository.findAll();
    }

    public Integer getLastOrder(){

       if(ordersRepository.findAll().stream()
               .mapToInt(t -> Integer.parseInt(t.getOrderNumber())).max().isPresent()){
           return ordersRepository.findAll().stream()
                   .mapToInt(t -> Integer.parseInt(t.getOrderNumber())).max().getAsInt();
       } else {
           return 0;
       }
    }


    public void addNewOrder(String address, String orderTime, String paymentType, String status, String pizzeriaName, String userId, String orderPrice){
        Integer order = getLastOrder() + 1;
        ordersRepository.addNewOrder(address, order, orderTime, paymentType, status, pizzeriaName, userId, orderPrice);
        if(!ordersRepository.findAllByOrderNumber(String.valueOf(order)).get(0).getStatus().equals("DONE")
                && !ordersRepository.findAllByOrderNumber(String.valueOf(order)).get(0).getStatus().equals("CANCELLED")){
            changeStatus(order);
        }
    }



    public void changeStatus(Integer order) {
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Task performed on: " + new Date() + "n" +
                        "Thread's name: " + Thread.currentThread().getName());
                ordersRepository.setNewStatus(String.valueOf(order), "COURIER");
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 600000;
        timer.schedule(task, delay);
    }


    public void addNewComposition(Integer foodCount, String foodName){
        System.out.println(String.valueOf(getLastOrder() + 1));
        orderCompositionRepository.addNewComposite(foodCount, foodName, String.valueOf(getLastOrder() + 1));
    }

    public List<Orders> getOrdersByUser(String userId){
        return ordersRepository.findAllByUserId(userId);
    }

    public List<OrderComposition> getOrdersCompositionByOrderNumber(String orderNumber){
        return orderCompositionRepository.findAllByOrderNumber(orderNumber);
    }

    public List<Orders> getActiveOrders(String user){
        return ordersRepository.findActiveOrder(user);
    }
}
