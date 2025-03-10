package com.AuthRole.Auth.model;


import com.AuthRole.Auth.model.Auth.user.AppUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority taskPriority;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private AppUser assignedUser;

    @ManyToOne
    @JoinColumn(name = "kanban_column_id", nullable = false)
    private KanbanColumn kanbanColumn;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> Comments;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @Column(name = "task_order") // Ensure this matches the database column name
    private Integer order;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
