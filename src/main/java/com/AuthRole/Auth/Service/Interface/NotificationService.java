package com.AuthRole.Auth.Service.Interface;

import com.AuthRole.Auth.model.DTO.NotificationDto;
import com.AuthRole.Auth.model.Response.NotificationResponse;

import java.util.List;

public interface NotificationService {
    NotificationResponse createNotification(NotificationDto notificationDto);

    NotificationResponse updateNotification(Long id, NotificationDto notificationDto);

    void deleteNotification(Long id);

    NotificationResponse getNotificationById(Long id);

    List<NotificationResponse> getAllNotifications();

    List<NotificationResponse> getNotificationsByUserId(String userId);
}
