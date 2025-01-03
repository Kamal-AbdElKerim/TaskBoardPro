package com.AuthRole.Auth.repository;


import com.AuthRole.Auth.model.Auth.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findByAppUserId(Long userId);

    // Find all UserRole entities for a specific Role
    List<UserRole> findByRoleId(Long roleId);

    @Query("SELECT ur FROM UserRole ur JOIN FETCH ur.appUser u JOIN FETCH ur.role r")
    List<UserRole> findAllUsersWithRoles();
}

