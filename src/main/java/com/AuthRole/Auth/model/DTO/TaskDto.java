package com.AuthRole.Auth.model.DTO;


import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.TaskPriority;
import com.AuthRole.Auth.model.TaskStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskDto {
    private Long id;

    @NotBlank(message = "Task title is required.")
    @Size(min = 3, max = 100, message = "Task title must be between 3 and 100 characters.")
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters.")
    private String description;

    private LocalDateTime startDate;

    @Future(message = "End date must be in the future.")
    private LocalDateTime endDate;

    @NotNull(message = "Task priority is required.")
    private TaskPriority taskPriority;
    private String assignedUserID;
    private Long kanbanColumnID; // Many-to-One relation
    private List<TicketsDto> tickets; // One-to-Many relation
}
