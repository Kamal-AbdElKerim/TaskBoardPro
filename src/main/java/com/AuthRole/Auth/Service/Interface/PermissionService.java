package com.AuthRole.Auth.Service.Interface;

import com.AuthRole.Auth.model.DTO.PermissionDto;
import com.AuthRole.Auth.model.Response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse createPermission(PermissionDto permissionDto);
    PermissionResponse updatePermission(Long id, PermissionDto permissionDto);
    void deletePermission(Long id);
    PermissionResponse getPermissionById(Long id);
    List<PermissionResponse> getPermissionsByProjectId(Long projectId);
}
