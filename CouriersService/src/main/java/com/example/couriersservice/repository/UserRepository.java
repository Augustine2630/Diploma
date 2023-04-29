package com.example.couriersservice.repository;

import com.example.couriersservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query("update User c set c.isOnWorkCour = ?1 where c.id = ?2")
    void updateIsOnWork(Boolean isOnWork, Long courierId);
}