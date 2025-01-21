package com.AuthRole.Auth.model.Response;

import com.AuthRole.Auth.model.TaskStatus;
import lombok.Data;

@Data
public class TicketResponse {
    private Long id;
    private String name;
    private TaskStatus taskStatus;
    private Long taskId;  // Reference to Task ID
}
