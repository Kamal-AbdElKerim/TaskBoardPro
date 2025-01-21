package com.AuthRole.Auth.repository;


import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByCreatedBy(AppUser createdBy);

    Optional<Project> findByName(String name);

}
