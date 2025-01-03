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

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority taskPriority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private AppUser assignedUser;

    @ManyToOne
    @JoinColumn(name = "kanban_column_id", nullable = false)
    private KanbanColumn kanbanColumn;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> Comments;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
