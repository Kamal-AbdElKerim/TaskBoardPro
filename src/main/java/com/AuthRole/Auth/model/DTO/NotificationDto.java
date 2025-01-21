package com.AuthRole.Auth.model.DTO;


import com.AuthRole.Auth.model.Auth.user.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto {

    @NotBlank(message = "Message is required.")
    private String message;

    @NotNull(message = "Read status is required.")
    private Boolean read;


    private String userID; // Many-to-One relation
    private LocalDateTime createdAt;
}
