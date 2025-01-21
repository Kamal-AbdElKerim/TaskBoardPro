package com.AuthRole.Auth.model.DTO;


import com.AuthRole.Auth.model.PermissionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PermissionDto {
    private Long id;

    @NotNull(message = "Permission type is required.")
    private PermissionType permissionType;

    private Long projectID; // Many-to-One relation
    private String userID; // Many-to-One relation
}
