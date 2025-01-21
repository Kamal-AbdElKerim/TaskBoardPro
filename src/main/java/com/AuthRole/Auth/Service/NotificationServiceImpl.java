package com.AuthRole.Auth.Service;

import com.AuthRole.Auth.Exception.EntityNotFoundException;
import com.AuthRole.Auth.Service.Interface.NotificationService;
import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.DTO.NotificationDto;
import com.AuthRole.Auth.model.Notification;
import com.AuthRole.Auth.model.MapStruct.NotificationMapper;
import com.AuthRole.Auth.model.Response.NotificationResponse;
import com.AuthRole.Auth.repository.NotificationRepository;
import com.AuthRole.Auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   UserRepository userRepository,
                                   NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationResponse createNotification(NotificationDto notificationDto) {
        AppUser user = userRepository.findById(notificationDto.getUserID())
                .orElseThrow(() -> new EntityNotFoundException("User","User not found"));

        Notification notification = notificationMapper.toEntity(notificationDto);
        notification.setUser(user);

        Notification savedNotification = notificationRepository.save(notification);
        return notificationMapper.toResponse(savedNotification);
    }

    @Override
    public NotificationResponse updateNotification(Long id, NotificationDto notificationDto) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification","Notification not found"));

        notification.setMessage(notificationDto.getMessage());
        notification.setRead(notificationDto.getRead());
        notification.setCreatedAt(notificationDto.getCreatedAt());

        Notification updatedNotification = notificationRepository.save(notification);
        return notificationMapper.toResponse(updatedNotification);
    }

    @Override
    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification","Notification not found"));

        notificationRepository.delete(notification);
    }

    @Override
    public NotificationResponse getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification","Notification not found"));

        return notificationMapper.toResponse(notification);
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(notificationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponse> getNotificationsByUserId(String userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Notification","Notification not found"));

        return notificationRepository.findByUser(user).stream()
                .map(notificationMapper::toResponse)
                .collect(Collectors.toList());
    }
}
