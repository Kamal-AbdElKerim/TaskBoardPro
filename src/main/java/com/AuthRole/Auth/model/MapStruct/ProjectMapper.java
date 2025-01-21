package com.AuthRole.Auth.model.MapStruct;


import com.AuthRole.Auth.model.DTO.ProjectDTO;
import com.AuthRole.Auth.model.Project;
import com.AuthRole.Auth.model.Response.ProjectResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {KanbanColumnMapper.class, PermissionMapper.class})
public interface ProjectMapper {

    @Mapping(target = "kanbanColumns", source = "kanbanColumns")
    @Mapping(target = "permissions", source = "permissions")
    ProjectResponse toResponse(Project project);

    @Mapping(target = "createdBy", ignore = true)
    Project toEntity(ProjectDTO projectDTO);

    @Mapping(target = "idCreatedBy", ignore = true)
    ProjectDTO toDTO(Project project);
}

