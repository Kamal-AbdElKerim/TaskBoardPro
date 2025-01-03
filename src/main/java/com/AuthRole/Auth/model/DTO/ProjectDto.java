package com.AuthRole.Auth.model.DTO;


import com.AuthRole.Auth.model.Auth.user.UserDto;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDto {
    private Long id;

    @NotBlank(message = "Project name is required.")
    @Size(min = 3, max = 100, message = "Project name must be between 3 and 100 characters.")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters.")
    private String description;

    private List<TaskDto> tasks; // One-to-Many relation
    private List<PermissionDto> permissions; // One-to-Many relation
    private UserDto owner; // Many-to-One relation
}
