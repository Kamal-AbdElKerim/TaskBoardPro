package com.AuthRole.Auth.model.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime generatedAt;
    private Boolean isSuccessful;
    private Long project;  // Just the project ID in the response
}
