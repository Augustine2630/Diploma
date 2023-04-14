package com.aug.productsservice.repository;

import com.aug.productsservice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO orders (address, order_number, order_time, payment_type, status) VALUES (?1, ?2, ?3, ?4, ?5)")
    void addNewOrder(String address, String orderNumber, String orderTime, String paymentType, String status);
}