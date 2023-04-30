package com.aug.productsservice.repository;

import com.aug.productsservice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO orders (address, order_number, order_time, payment_type, status, pizzeria_name, user_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)")
    void addNewOrder(String address, Integer orderNumber, String orderTime, String paymentType, String status, String pizzeriaName, String userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE orders\n" +
            "SET status = ?2\n" +
            "WHERE order_number = ?1")
    void setNewStatus(String orderNumber, String orderStatus);


    List<Orders> findAllByOrderNumber(String orderNumber);

    List<Orders> findAllByPizzeriaName(String pizzeriaName);


    @Query("select o from Orders o where o.status = 'DONE' AND o.userId = ?1")
    List<Orders> findAllByUserId(String userId);

    @Query("select o from Orders o where o.status = 'IN PROGRESS' OR o.status = 'COURIER' AND o.userId = ?1")
    List<Orders> findActiveOrder(String userId);


}