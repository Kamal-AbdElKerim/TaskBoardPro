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
                .orElseThrow(() -> new EntityNotFoundException("kanbanColumn","kanbanColumn not found"));

        Task task = taskMapper.toEntity(taskDTO);
        task.setKanbanColumn(kanbanColumn);
        task.setTaskPriority(TaskPriority.LOW);

        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponse(savedTask);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskDto taskDTO) {
        // Fetch the existing task from the repository
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task","Task not found"));

        // Update the fields of the existing task object with values from taskDTO
        if (taskDTO.getTitle() != null && !taskDTO.getTitle().isEmpty()) {
            task.setTitle(taskDTO.getTitle());  // Update task title
        }

        if (taskDTO.getDescription() != null && !taskDTO.getDescription().isEmpty()) {
            task.setDescription(taskDTO.getDescription());  // Update description
        }

        if (taskDTO.getStartDate() != null ) {
            task.setStartDate(taskDTO.getStartDate());  // Update start date
        }

        if (taskDTO.getEndDate() != null) {
            task.setEndDate(taskDTO.getEndDate());  // Update end date
        }

        if (taskDTO.getTaskPriority() != null) {
            task.setTaskPriority(taskDTO.getTaskPriority());  // Update task priority
        }
        System.out.println("taskDTO.getAssignedUserID()" + taskDTO.getAssignedUserID());
        if (taskDTO.getAssignedUserID() != null) {
            AppUser assignedTo = userRepository.findById(taskDTO.getAssignedUserID())
                    .orElseThrow(() -> new EntityNotFoundException("User","User not found"));
            task.setAssignedUser(assignedTo);  // Update assigned user
        }

        if (taskDTO.getKanbanColumnID() != null) {
            KanbanColumn kanbanColumn = kanbanColumnRepository.findById(taskDTO.getKanbanColumnID())
                    .orElseThrow(() -> new EntityNotFoundException("Kanban" ,"Kanban column not found"));
            task.setKanbanColumn(kanbanColumn);  // Update Kanban column
        }

        // Save the updated task to the database
        Task updatedTask = taskRepository.save(task);

        // Return the updated task as a response
        return taskMapper.toResponse(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task","Task not found"));

        taskRepository.delete(task);
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task" ,"Task not found"));

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
