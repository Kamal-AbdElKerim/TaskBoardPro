package com.AuthRole.Auth.Service.Interface;


import com.AuthRole.Auth.model.DTO.TaskDto;
import com.AuthRole.Auth.model.Response.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskDto taskDTO);

    TaskResponse updateTask(Long id, TaskDto taskDTO);

    void deleteTask(Long id);

    TaskResponse getTaskById(Long id);

    List<TaskResponse> getAllTasks();

    List<TaskResponse> getAllTasksByKanbanColumn(Long KanbanColumnID);
}
