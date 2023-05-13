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
    @Query(nativeQuery = true, value = "UPDATE orders \n" +
            "SET  courier_id = ?2 \n" +
            "WHERE id = ?1")
    void courierAcceptOrder(Integer orderId, String courier);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE orders " +
            "SET  status = 'COURIER' " +
            "WHERE id = ?1")
    void courierAcceptOrderStatus(Integer orderId, String courier);

    @Query(nativeQuery = true, value = "SELECT * FROM ORDERS o WHERE o.courier_id = ?1 AND o.status ='DONE'")
    List<Orders> findAllByCourierId(String courier);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE orders\n" +
            "SET status = ?2 \n" +
            "WHERE id = ?1 ")
    void setNewStatus(Integer orderNumber, String orderStatus);

    List<Orders> findOrdersByCourierIdAndStatus(String courier, String status);
}