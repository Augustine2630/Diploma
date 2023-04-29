package com.example.couriersservice.repository;

import com.example.couriersservice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByPizzeriaName(String pizzeriaName);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE orders " +
            "SET  courier_id = ?2 AND status = 'COURIER' " +
            "WHERE id = ?1")
    void courierAcceptOrder(Integer orderId, String courier);

    @Query(nativeQuery = true, value = "SELECT * FROM ORDERS WHERE courier_id = ?1 ")
    List<Orders> findAllByCourierId(String courier);
}