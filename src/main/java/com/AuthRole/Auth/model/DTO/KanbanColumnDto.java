package com.AuthRole.Auth.model.DTO;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class KanbanColumnDto {
    @NotBlank(message = "Column name is required.")
    private String name;

    @NotNull(message = "Position is required.")
    private Integer position;

    @NotNull(message = "Project ID is required.")
    private Long projectID;

}
