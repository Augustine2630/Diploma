package com.example.couriersservice.service;

import com.example.couriersservice.model.Orders;
import com.example.couriersservice.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;

    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }


    public List<Orders> getAllByPizzeriaName(String pizzeriaName){
        return ordersRepository.findAllByPizzeriaName(pizzeriaName);
    }

    public void acceptOrder(Integer orderId, String courier){
        ordersRepository.courierAcceptOrder(orderId, courier);
        ordersRepository.courierAcceptOrderStatus(orderId, courier);
    }

    public List<Orders> getAllByCourier(String courier){
        return ordersRepository.findAllByCourierId(courier);
    }
}
