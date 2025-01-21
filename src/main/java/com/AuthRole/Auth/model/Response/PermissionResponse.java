package com.AuthRole.Auth.model.Response;


import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.Auth.user.UserResponseDto;
import com.AuthRole.Auth.model.PermissionType;
import com.AuthRole.Auth.model.Project;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class PermissionResponse {
    private Long id;

    private PermissionType permissionType;

    private UserResponseDto user;

    private LocalDateTime createdAt;

    private ProjectResponse project;
}
