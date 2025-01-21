package com.AuthRole.Auth.model.MapStruct;

import com.AuthRole.Auth.model.DTO.PermissionDto;
import com.AuthRole.Auth.model.Permission;
import com.AuthRole.Auth.model.Response.PermissionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    // Map Permission to PermissionResponse
    PermissionResponse toResponse(Permission permission);

    // Map PermissionDTO to Permission
    Permission toEntity(PermissionDto permissionDTO);

    // Map Permission to PermissionDTO
    PermissionDto toDTO(Permission permission);
}

