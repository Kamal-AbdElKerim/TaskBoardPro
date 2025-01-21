package com.AuthRole.Auth.Service;

import com.AuthRole.Auth.Exception.EntityNotFoundException;
import com.AuthRole.Auth.Service.Interface.TicketService;
import com.AuthRole.Auth.model.DTO.TicketsDto;
import com.AuthRole.Auth.model.KanbanColumn;
import com.AuthRole.Auth.model.MapStruct.TicketMapper;
import com.AuthRole.Auth.model.Response.TicketResponse;
import com.AuthRole.Auth.model.TaskStatus;
import com.AuthRole.Auth.model.Ticket;
import com.AuthRole.Auth.model.Task;
import com.AuthRole.Auth.repository.TicketRepository;
import com.AuthRole.Auth.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TaskRepository taskRepository;
    private final TicketMapper ticketMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, TaskRepository taskRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.taskRepository = taskRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public TicketResponse createTicket(TicketsDto ticketsDto) {
        // Fetch associated task
        Task task = taskRepository.findById(ticketsDto.getTaskID())
                .orElseThrow(() -> new EntityNotFoundException("Task", "Task not found"));

        // Create and save ticket
        Ticket ticket = ticketMapper.toEntity(ticketsDto);
        ticket.setTask(task);  // Set the task associated with the ticket
        ticket.setTaskStatus(TaskStatus.TODO);
        Ticket savedTicket = ticketRepository.save(ticket);

        return ticketMapper.toResponse(savedTicket);
    }

    @Override
    public TicketResponse updateTicket(Long id, TicketsDto ticketsDto) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket", "Ticket not found"));

        // Map updated fields from DTO
        if (ticketsDto.getName() != null && !ticketsDto.getName().isEmpty()) {
            ticket.setName(ticketsDto.getName());
        }
        if (ticketsDto.getTaskStatus() != null ){
            ticket.setTaskStatus(ticketsDto.getTaskStatus());
        }

        // Save updated ticket
        Ticket updatedTicket = ticketRepository.save(ticket);

        return ticketMapper.toResponse(updatedTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket", "Ticket not found"));

        ticketRepository.delete(ticket);
    }

    @Override
    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket", "Ticket not found"));

        return ticketMapper.toResponse(ticket);
    }

    @Override
    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketResponse> getTicketsByTaskId(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("kanbanColumn" ,"kanbanColumn not found"));

        return ticketRepository.findByTask(task).stream()
                .map(ticketMapper::toResponse)
                .collect(Collectors.toList());
    }
}
