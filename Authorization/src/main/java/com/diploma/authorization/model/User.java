package com.diploma.authorization.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "security_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;


    private String birthDate;

    @Column(name = "card_number")
    private String cardNumber;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Role> roles = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "department_id")
    @JsonManagedReference
    private Department department;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Addresses> addresseses = new ArrayList<>();

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<Addresses> getAddresseses() {
        return addresseses;
    }

    public void setAddresseses(List<Addresses> addresseses) {
        this.addresseses = addresseses;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

}
