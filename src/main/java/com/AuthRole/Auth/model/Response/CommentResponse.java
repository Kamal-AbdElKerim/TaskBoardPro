package com.AuthRole.Auth.model.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long id;
    private String content;
    private Long taskID;
    private String userID;
    private LocalDateTime createdAt;
}
