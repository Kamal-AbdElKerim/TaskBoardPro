package com.AuthRole.Auth.model.Response;

import com.AuthRole.Auth.model.PermissionType;
import lombok.Data;

@Data
public class PermissionResponse {
    private Long id;
    private PermissionType permissionType;
    private Long projectId;
    private Long userID;
}
