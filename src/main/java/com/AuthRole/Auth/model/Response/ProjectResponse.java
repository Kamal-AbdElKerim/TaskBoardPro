package com.AuthRole.Auth.model.Response;

import java.time.LocalDateTime;
import java.util.List;

import com.AuthRole.Auth.model.Auth.user.UserResponseDto;
import lombok.Data;

@Data
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private UserResponseDto createdBy;
    private LocalDateTime createdAt;
    private List<KanbanColumnResponse> kanbanColumns;
    private List<PermissionResponse> permissions;
}
