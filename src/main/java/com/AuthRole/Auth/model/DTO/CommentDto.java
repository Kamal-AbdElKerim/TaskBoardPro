package com.AuthRole.Auth.model.DTO;


import com.AuthRole.Auth.model.Auth.user.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private Long id;

    @NotBlank(message = "Content is required.")
    private String content;


    @NotNull(message = "Task is required.")
    private Long taskID; // Many-to-One relationship

    @NotNull(message = "User is required.")
    private String userID; // Many-to-One relationship
}
