package com.AuthRole.Auth.repository;

import com.AuthRole.Auth.model.Task;
import com.AuthRole.Auth.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

      List<Ticket> findByTask(Task task);  // Change to return List<Ticket> instead of a single Ticket
}
