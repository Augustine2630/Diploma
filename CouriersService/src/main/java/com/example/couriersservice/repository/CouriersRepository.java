package com.example.couriersservice.repository;

import com.example.couriersservice.model.Couriers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouriersRepository extends JpaRepository<Couriers, Long> {
}