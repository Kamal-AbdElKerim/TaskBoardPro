package com.AuthRole.Auth.Service;

import com.AuthRole.Auth.Exception.EntityNotFoundException;
import com.AuthRole.Auth.Service.Interface.CommentService;
import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.Comment;
import com.AuthRole.Auth.model.DTO.CommentDto;
import com.AuthRole.Auth.model.MapStruct.CommentMapper;
import com.AuthRole.Auth.model.Response.CommentResponse;
import com.AuthRole.Auth.repository.CommentRepository;
import com.AuthRole.Auth.repository.TaskRepository;
import com.AuthRole.Auth.repository.UserRepository;
import com.AuthRole.Auth.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper; // Dependency injected

    public CommentServiceImpl(CommentRepository commentRepository,
                              TaskRepository taskRepository,
                              UserRepository userRepository,
                              CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper; // Injected via constructor
    }

    @Override
    public CommentResponse createComment(CommentDto commentDto) {
        Task task = taskRepository.findById(commentDto.getTaskID())
                .orElseThrow(() -> new EntityNotFoundException("Task","Task not found"));

        AppUser user = userRepository.findById(commentDto.getUserID())
                .orElseThrow(() -> new EntityNotFoundException("User","User not found"));

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setTask(task);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);

        return commentMapper.toResponse(savedComment);
    }

    @Override
    public CommentResponse updateComment(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment","Comment not found"));

        comment.setContent(commentDto.getContent());
        Comment updatedComment = commentRepository.save(comment);

        return commentMapper.toResponse(updatedComment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment","Comment not found"));

        commentRepository.delete(comment);
    }

    @Override
    public CommentResponse getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment","Comment not found"));

        return commentMapper.toResponse(comment);
    }

    @Override
    public List<CommentResponse> getCommentsByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId).stream()
                .map(commentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
