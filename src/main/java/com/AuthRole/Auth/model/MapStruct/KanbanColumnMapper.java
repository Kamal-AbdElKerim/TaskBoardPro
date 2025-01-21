package com.AuthRole.Auth.model.MapStruct;

import com.AuthRole.Auth.model.DTO.KanbanColumnDto;
import com.AuthRole.Auth.model.KanbanColumn;
import com.AuthRole.Auth.model.Response.KanbanColumnResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface KanbanColumnMapper {

    @Mapping(target = "projectID", source = "project.id") // Map project ID to projectID
    KanbanColumnResponse toResponse(KanbanColumn kanbanColumn);

    KanbanColumn toEntity(KanbanColumnDto kanbanColumnDto);

    @Mapping(target = "projectID", source = "project.id") // Map project ID to projectID in the list
    List<KanbanColumnResponse> toResponseList(List<KanbanColumn> kanbanColumns);
}


