package com.AuthRole.Auth.model.MapStruct;


import com.AuthRole.Auth.model.DTO.TicketsDto;
import com.AuthRole.Auth.model.Response.TicketResponse;
import com.AuthRole.Auth.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    // Map Ticket entity to TicketResponse (used for sending data to the client)
    @Mapping(target = "taskId", source = "task.id")
    TicketResponse toResponse(Ticket ticket);

    // Map TicketsDto to Ticket entity (used for saving/updating data)
    Ticket toEntity(TicketsDto ticketsDto);

    // Map Ticket entity to TicketsDto (used for sending data to the client in a form that's easier to use)
    // You can ignore the `task` field if it's handled separately
    TicketsDto toDTO(Ticket ticket);
}
