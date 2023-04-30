package com.diploma.authorization.repository;

import com.diploma.authorization.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(nativeQuery = true, value = "INSERT INTO security_roles(role, user_id) VALUES (?1, ?2)")
    void addNewRoleWithUser(String role, Integer userId);

}