package com.aug.productsservice.service;

import com.aug.productsservice.model.Orders;
import com.aug.productsservice.repository.OrderCompositionRepository;
import com.aug.productsservice.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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


    public void addNewOrder(String address, String orderTime, String paymentType, String status){

        ordersRepository.addNewOrder(address, String.valueOf(getLastOrder() + 1), orderTime, paymentType, status);

    }

    public void addNewComposition(Integer foodCount, String foodName){
        System.out.println(String.valueOf(getLastOrder() + 1));
        orderCompositionRepository.addNewComposite(foodCount, foodName, String.valueOf(getLastOrder() + 1));
    }
}
