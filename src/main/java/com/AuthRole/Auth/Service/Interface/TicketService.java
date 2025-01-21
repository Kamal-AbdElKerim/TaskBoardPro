package com.AuthRole.Auth.Service.Interface;


import com.AuthRole.Auth.model.DTO.TicketsDto;
import com.AuthRole.Auth.model.Response.TicketResponse;
import com.AuthRole.Auth.model.Ticket;

import java.util.List;

public interface TicketService {

    TicketResponse createTicket(TicketsDto ticketsDto);

    TicketResponse updateTicket(Long id, TicketsDto ticketsDto);

    void deleteTicket(Long id);

    TicketResponse getTicketById(Long id);

    List<TicketResponse> getAllTickets();

    List<TicketResponse> getTicketsByTaskId(Long taskId);
}
