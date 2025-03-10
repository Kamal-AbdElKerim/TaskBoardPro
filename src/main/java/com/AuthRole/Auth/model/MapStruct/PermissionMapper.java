package com.AuthRole.Auth.model.MapStruct;

import com.AuthRole.Auth.model.DTO.PermissionDto;
import com.AuthRole.Auth.model.KanbanColumn;
import com.AuthRole.Auth.model.Permission;
import com.AuthRole.Auth.model.Response.KanbanColumnResponse;
import com.AuthRole.Auth.model.Response.PermissionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface PermissionMapper {

    // Mapping from PermissionDto to Permission (entity)
    @Mapping(source = "userID", target = "user.id")  // Map userID in PermissionDto to user.id in Permission entity
    @Mapping(source = "projectID", target = "project.id")  // Map projectID in PermissionDto to project.id in Permission entity
    Permission toEntity(PermissionDto dto);

    // Mapping from Permission entity to PermissionResponse
    @Mapping(source = "user.id", target = "userID")  // Map user.id from Permission entity to userID in PermissionResponse
    @Mapping(source = "project.id", target = "projectId")  // Map project.id from Permission entity to projectId in PermissionResponse
    PermissionResponse toResponse(Permission permission);

    List<PermissionResponse> toResponseList(List<Permission> permissions);

}

