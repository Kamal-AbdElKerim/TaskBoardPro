package com.AuthRole.Auth.model.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponse {
    private Long id; // The notification ID
    private String message; // The notification message
    private Boolean read; // Read status
    private Long userID; // Associated user ID
    private LocalDateTime createdAt; // Timestamp when the notification was created
}
