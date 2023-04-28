package com.example.couriersservice.service;

import com.example.couriersservice.repository.CouriersRepository;
import org.springframework.stereotype.Service;

@Service
public class CourierService {

    private final CouriersRepository couriersRepository;

    public CourierService(CouriersRepository couriersRepository) {
        this.couriersRepository = couriersRepository;
    }


}
