package com.diploma.authorization.repository;

import com.diploma.authorization.model.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressesRepository extends JpaRepository<Addresses, Long> {

    @Query("select a from Addresses a where a.user = ?1")
    List<Addresses> findAllByUser(String user);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO addresses(address, user_address) VALUES (?1, ?2)", nativeQuery = true)
    void addNewAddress(String address, String user);
}