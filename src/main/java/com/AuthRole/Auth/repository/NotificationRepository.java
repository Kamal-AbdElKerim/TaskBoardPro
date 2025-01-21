package com.AuthRole.Auth.repository;

import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(AppUser user);
}
