package com.AuthRole.Auth.model.DTO;


import com.AuthRole.Auth.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketsDto {
    private Long id;

    @NotBlank(message = "Ticket name is required.")
    private String name;

    private TaskStatus taskStatus;

    private Long taskID;
}

