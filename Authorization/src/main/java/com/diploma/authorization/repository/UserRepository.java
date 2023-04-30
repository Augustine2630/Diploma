package com.diploma.authorization.repository;

import com.diploma.authorization.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE security_users " +
            "SET  address = ?1 " +
            "WHERE id = ?2")
    void setUserAddress(String address, Integer userId);


}