package com.AuthRole.Auth.repository;

import java.util.Optional;


import com.AuthRole.Auth.model.Auth.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String roleName);
}
