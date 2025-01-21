package com.AuthRole.Auth.Controller;

import com.AuthRole.Auth.Service.Interface.TicketService;
import com.AuthRole.Auth.model.DTO.TicketsDto;
import com.AuthRole.Auth.model.Response.TicketResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@RequestBody @Valid TicketsDto ticketsDto) {
        TicketResponse ticketResponse = ticketService.createTicket(ticketsDto);
        return new ResponseEntity<>(ticketResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(@PathVariable Long id, @RequestBody TicketsDto ticketsDto) {
        TicketResponse ticketResponse = ticketService.updateTicket(id, ticketsDto);
        return new ResponseEntity<>(ticketResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long id) {
        TicketResponse ticketResponse = ticketService.getTicketById(id);
        return new ResponseEntity<>(ticketResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        List<TicketResponse> ticketResponses = ticketService.getAllTickets();
        return new ResponseEntity<>(ticketResponses, HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<TicketResponse>> getTicketsByTaskId(@PathVariable Long taskId) {
        List<TicketResponse> ticketResponses = ticketService.getTicketsByTaskId(taskId);
        return new ResponseEntity<>(ticketResponses, HttpStatus.OK);
    }
}
