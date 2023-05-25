package com.aug.adminservice.service;


import com.aug.adminservice.model.OrderComposition;
import com.aug.adminservice.model.Orders;
import com.aug.adminservice.repository.OrderCompositionRepository;
import com.aug.adminservice.repository.OrdersRepository;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

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
        List<Orders> orders = ordersRepository.findAll();
        orders.sort((d1, d2) -> Integer.parseInt(d2.getOrderNumber()) - Integer.parseInt(d1.getOrderNumber()));
        return orders;
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


    public void addNewOrder(String address, String orderTime, String paymentType, String status, String pizzeriaName){
        Integer order = getLastOrder() + 1;
        ordersRepository.addNewOrder(address, order, orderTime, paymentType, status, pizzeriaName);
        if(!ordersRepository.findAllByOrderNumber(String.valueOf(order)).get(0).getStatus().equals("DONE")
                || !ordersRepository.findAllByOrderNumber(String.valueOf(order)).get(0).getStatus().equals("CANCELLED")){
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

        long delay = 10000;
        timer.schedule(task, delay);
    }

    public void addNewComposition(Integer foodCount, String foodName){
        System.out.println(String.valueOf(getLastOrder() + 1));
        orderCompositionRepository.addNewComposite(foodCount, foodName, getLastOrder() + 1);
    }

    public void changeOrderStatus(String orderNumber, String orderStatus){
        ordersRepository.setNewStatus(orderNumber, orderStatus);
    }

    public List<Orders> getAllByOrderNumber(String pizzeriaName){
        return ordersRepository.findAllByOrderNumber(pizzeriaName);
    }

    public List<OrderComposition> getAllCompositionByOrderNumber(String orderNumber){
        return orderCompositionRepository.findAllOrderCompositionByOrderNumber(orderNumber);
    }

    public List<Orders> getAllByPizzeriaName(String pizzeriaName){
        return ordersRepository.findAllByPizzeriaName(pizzeriaName);
    }
}
