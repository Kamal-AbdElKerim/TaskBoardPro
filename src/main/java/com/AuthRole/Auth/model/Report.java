package com.AuthRole.Auth.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime generatedAt;

    @Column(nullable = false)
    private boolean isSuccessful;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

}
