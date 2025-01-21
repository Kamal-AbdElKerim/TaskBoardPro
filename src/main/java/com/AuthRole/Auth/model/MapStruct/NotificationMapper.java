package com.AuthRole.Auth.model.MapStruct;

import com.AuthRole.Auth.model.DTO.NotificationDto;
import com.AuthRole.Auth.model.Notification;
import com.AuthRole.Auth.model.Response.NotificationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Notification toEntity(NotificationDto notificationDto);

    NotificationResponse toResponse(Notification notification);
}
