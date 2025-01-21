package com.AuthRole.Auth.Controller;

import com.AuthRole.Auth.Service.Interface.TaskService;
import com.AuthRole.Auth.model.DTO.TaskDto;
import com.AuthRole.Auth.model.Response.TaskResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskDto taskDTO) {
        TaskResponse taskResponse = taskService.createTask(taskDTO);
        return ResponseEntity.ok(taskResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDTO) {
        TaskResponse taskResponse = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(taskResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        TaskResponse taskResponse = taskService.getTaskById(id);
        return ResponseEntity.ok(taskResponse);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/KanbanColumn/{KanbanColumnID}")
    public ResponseEntity<List<TaskResponse>> getAllTasksByKanbanColumn(@PathVariable Long KanbanColumnID) {
        List<TaskResponse> tasks = taskService.getAllTasksByKanbanColumn(KanbanColumnID);
        return ResponseEntity.ok(tasks);
    }
}
