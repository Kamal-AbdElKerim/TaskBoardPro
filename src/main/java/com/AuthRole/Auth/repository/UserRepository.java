package com.AuthRole.Auth.repository;

import java.util.Optional;

import com.AuthRole.Auth.model.Auth.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

  Optional<AppUser> findByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  Optional<AppUser> findByEmail(String email);
}
