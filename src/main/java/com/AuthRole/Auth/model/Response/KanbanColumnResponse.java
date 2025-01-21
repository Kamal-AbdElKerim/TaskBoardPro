package com.AuthRole.Auth.model.Response;
import com.AuthRole.Auth.model.Project;
import com.AuthRole.Auth.model.Task;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class KanbanColumnResponse {
    private Long id;

    private String name;

    private Integer position;

    private Long projectID;

    private List<TaskResponse> tasks;

    private LocalDateTime createdAt;
}
