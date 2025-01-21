package com.AuthRole.Auth.model.Response;
import com.AuthRole.Auth.model.*;
import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.Auth.user.UserResponseDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskResponse {
    private Long id;

    private String title;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;


    private TaskPriority taskPriority;


    private UserResponseDto assignedUser;


    private Long kanbanColumnID;

    private List<CommentResponse> Comments;

    private List<TicketResponse> Tickets;

    private LocalDateTime createdAt;
}
