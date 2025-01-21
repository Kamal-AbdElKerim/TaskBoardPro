package com.AuthRole.Auth.Service;


import com.AuthRole.Auth.Exception.EntityNotFoundException;
import com.AuthRole.Auth.Service.Interface.TaskService;
import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.DTO.TaskDto;
import com.AuthRole.Auth.model.KanbanColumn;
import com.AuthRole.Auth.model.MapStruct.TaskMapper;
import com.AuthRole.Auth.model.Project;
import com.AuthRole.Auth.model.Response.TaskResponse;
import com.AuthRole.Auth.model.Task;
import com.AuthRole.Auth.model.TaskPriority;
import com.AuthRole.Auth.repository.KanbanColumnRepository;
import com.AuthRole.Auth.repository.ProjectRepository;
import com.AuthRole.Auth.repository.TaskRepository;
import com.AuthRole.Auth.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final KanbanColumnRepository kanbanColumnRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository,
                           KanbanColumnRepository kanbanColumnRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.kanbanColumnRepository = kanbanColumnRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskResponse createTask(TaskDto taskDTO) {
        KanbanColumn kanbanColumn = kanbanColumnRepository.findById(taskDTO.getKanbanColumnID())
                .orElseThrow(() -> new RuntimeException("kanbanColumn not found"));

        Task task = taskMapper.toEntity(taskDTO);
        task.setKanbanColumn(kanbanColumn);
        task.setTaskPriority(TaskPriority.LOW);

        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponse(savedTask);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskDto taskDTO) {
//        Task task = taskRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Task not found"));

        AppUser assignedTo = userRepository.findById(taskDTO.getAssignedUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));


        Task Updatetask = taskMapper.toEntity(taskDTO);


        Updatetask.setAssignedUser(assignedTo);

        Task updatedTask = taskRepository.save(Updatetask);
        return taskMapper.toResponse(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return taskMapper.toResponse(task);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> getAllTasksByKanbanColumn(Long kanbanColumnID) {
        KanbanColumn kanbanColumn = kanbanColumnRepository.findById(kanbanColumnID)
                .orElseThrow(() -> new EntityNotFoundException("kanbanColumn" ,"kanbanColumn not found"));

        return taskRepository.findByKanbanColumn(kanbanColumn).stream()
                .map(taskMapper::toResponse)
                .collect(Collectors.toList());
    }
}
