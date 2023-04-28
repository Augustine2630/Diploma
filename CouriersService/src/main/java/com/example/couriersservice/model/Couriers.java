package com.example.couriersservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Couriers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courierFullName;

    private String location;

    private Boolean isOnWork;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourierFullName() {
        return courierFullName;
    }

    public void setCourierFullName(String courierFullName) {
        this.courierFullName = courierFullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getIsOnWork() {
        return isOnWork;
    }

    public void setIsOnWork(Boolean onWork) {
        isOnWork = onWork;
    }
}
