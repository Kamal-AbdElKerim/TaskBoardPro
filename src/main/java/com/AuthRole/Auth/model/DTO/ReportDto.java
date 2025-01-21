package com.AuthRole.Auth.model.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportDto {
    private Long id;

    @NotBlank(message = "Report title is required.")
    private String title;

    private String description;

    private LocalDateTime generatedAt;

    private Boolean isSuccessful;

    private Long project; // Many-to-One relation
}
