package com.example.couriersservice.service;

import com.example.couriersservice.repository.CouriersRepository;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;

@Service
public class CourierService {

    private final CouriersRepository couriersRepository;

    public CourierService(CouriersRepository couriersRepository) {
        this.couriersRepository = couriersRepository;
    }

    public void updateCourierWork(Boolean isOnWord, Long courierId){
        couriersRepository.updateIsOnWork(isOnWord, courierId);
    }

}
