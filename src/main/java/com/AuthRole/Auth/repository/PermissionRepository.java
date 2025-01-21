package com.AuthRole.Auth.repository;

import com.AuthRole.Auth.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByProjectId(Long projectId);
}
