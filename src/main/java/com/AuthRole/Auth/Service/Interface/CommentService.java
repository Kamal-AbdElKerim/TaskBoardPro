package com.AuthRole.Auth.Service.Interface;

import com.AuthRole.Auth.model.DTO.CommentDto;
import com.AuthRole.Auth.model.Response.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(CommentDto commentDto);
    CommentResponse updateComment(Long id, CommentDto commentDto);
    void deleteComment(Long id);
    CommentResponse getCommentById(Long id);
    List<CommentResponse> getCommentsByTaskId(Long taskId);
}
