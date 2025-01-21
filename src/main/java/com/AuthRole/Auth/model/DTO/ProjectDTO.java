package com.AuthRole.Auth.model.DTO;


import com.AuthRole.Auth.model.Auth.user.UserDto;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ProjectDTO {

    private Long id;

    @NotBlank(message = "Project name is required.")
    @Size(min = 3, max = 100, message = "Project name must be between 3 and 100 characters.")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters.")
    private String description;

    @NotBlank(message = "Project IdCreatedBy is required.")
    private String idCreatedBy;

    private LocalDateTime createdAt;


}
