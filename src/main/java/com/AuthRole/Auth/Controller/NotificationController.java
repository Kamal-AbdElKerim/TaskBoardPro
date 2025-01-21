package com.AuthRole.Auth.Controller;

import com.AuthRole.Auth.Service.Interface.NotificationService;
import com.AuthRole.Auth.model.DTO.NotificationDto;
import com.AuthRole.Auth.model.Response.NotificationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@Validated @RequestBody NotificationDto notificationDto) {
        NotificationResponse response = notificationService.createNotification(notificationDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponse> updateNotification(@PathVariable Long id,
                                                                   @Validated @RequestBody NotificationDto notificationDto) {
        NotificationResponse response = notificationService.updateNotification(id, notificationDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id) {
        NotificationResponse response = notificationService.getNotificationById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        List<NotificationResponse> responses = notificationService.getAllNotifications();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponse>> getNotificationsByUserId(@PathVariable String userId) {
        List<NotificationResponse> responses = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(responses);
    }
}
