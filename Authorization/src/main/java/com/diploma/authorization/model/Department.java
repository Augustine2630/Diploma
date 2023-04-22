package com.diploma.authorization.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "pizzeria")
    private String pizzeria;

    @OneToOne(mappedBy = "department", orphanRemoval = true)
    @JsonBackReference
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPizzeria() {
        return pizzeria;
    }

    public void setPizzeria(String pizzeria) {
        this.pizzeria = pizzeria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
