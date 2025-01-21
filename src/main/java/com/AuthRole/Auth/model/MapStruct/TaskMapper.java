package com.AuthRole.Auth.model.MapStruct;


import com.AuthRole.Auth.model.DTO.TaskDto;
import com.AuthRole.Auth.model.Response.TaskResponse;
import com.AuthRole.Auth.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {TicketMapper.class})
public interface TaskMapper {

    // Map Task to TaskResponse
    @Mapping(target = "kanbanColumnID", source = "kanbanColumn.id")
    TaskResponse toResponse(Task task);

    // Map TaskDTO to Task
    @Mapping(target = "assignedUser", ignore = true)
    @Mapping(target = "kanbanColumn", ignore = true)
    Task toEntity(TaskDto taskDTO);

    @Mapping(target = "assignedUserID", ignore = true)
    @Mapping(target = "kanbanColumnID", ignore = true)
    TaskDto toDTO(Task task);
}
