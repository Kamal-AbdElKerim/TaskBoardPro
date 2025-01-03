package com.AuthRole.Auth.model.DTO;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class KanbanColumnDto {
    private Long id;

    @NotBlank(message = "Column name is required.")
    private String name;

    @NotNull(message = "Position is required.")
    @Min(value = 0, message = "Position must be greater than or equal to 0.")
    private Integer position;

    private ProjectDto project; // Many-to-One relation
    private List<TaskDto> tasks; // One-to-Many relation
}
