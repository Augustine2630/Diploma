package com.aug.productsservice.repository;

import com.aug.productsservice.model.OrderComposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface OrderCompositionRepository extends JpaRepository<OrderComposition, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO order_composition (food_count, food_name, order_number) VALUES (?1, ?2, ?3)")
    void addNewComposite(Integer foodCount, String foodName, String orderNumber);
}