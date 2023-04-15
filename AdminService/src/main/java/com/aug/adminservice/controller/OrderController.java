package com.aug.adminservice.controller;

import com.aug.adminservice.POJO.OrderRequest;
import com.aug.adminservice.model.OrderComposition;
import com.aug.adminservice.model.Orders;
import com.aug.adminservice.service.OrderFoodSamples;
import com.aug.adminservice.service.OrderService;
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
    public ResponseEntity<HttpStatus> reduceFoodCount(@RequestBody OrderRequest[] orderRequest){
        if ((orderRequest == null) || orderRequest.length == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        AtomicReference<String> address = new AtomicReference<>("");
        AtomicReference<String> pizzeriaName = new AtomicReference<>("");
        Arrays.stream(orderRequest).forEach(t -> {
            address.set(t.getAddress());
            pizzeriaName.set(t.getPizzeriaAddress());
            if(!(t.getFoodCount() == 0)){
                ordersService.addNewComposition(t.getFoodCount(), t.getFoodName());
            }
        });
        ordersService.addNewOrder(address.get(), dtf.format(now), "Card", "IN PROGRESS", pizzeriaName.get());


        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/get-all-orders")
    public List<Orders> getAll(){
        return ordersService.getAll();
    }



    @GetMapping("/order-new-status")
    public void setNewOrderStatus(@RequestParam("order_number") String orderNumber, @RequestParam("order_status") String orderStatus){
        ordersService.changeOrderStatus(orderNumber, orderStatus);
    }

    @GetMapping("/orders-pz-name")
    public List<Orders> getAllByOrderNumber(@RequestParam("order_number") String orderNumber){
        return ordersService.getAllByOrderNumber(orderNumber);
    }

    @GetMapping("/orders-comp-number")
    public List<OrderComposition> getAllCompositionByOrderNumber(@RequestParam("order_number") String orderNumber){
        return ordersService.getAllCompositionByOrderNumber(orderNumber);
    }

    @GetMapping("/orders-by-pz-name")
    public List<Orders> getAllByPizzeriaName(@RequestParam("pizzeria_name") String pizzeriaName){
        return ordersService.getAllByPizzeriaName(pizzeriaName);
    }
}
