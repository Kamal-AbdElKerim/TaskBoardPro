package com.AuthRole.Auth.Controller;


import com.AuthRole.Auth.Service.Interface.ProjectService;
import com.AuthRole.Auth.model.DTO.ProjectDTO;
import com.AuthRole.Auth.model.Response.ProjectResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectDTO projectDTO) {
        ProjectResponse projectResponse = projectService.createProject(projectDTO , projectDTO.getIdCreatedBy());
        return ResponseEntity.ok(projectResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        ProjectResponse projectResponse = projectService.updateProject(id, projectDTO);
        return ResponseEntity.ok(projectResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        ProjectResponse projectResponse = projectService.getProjectById(id);
        return ResponseEntity.ok(projectResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        List<ProjectResponse> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<List<ProjectResponse>> getAllProjectsByUser(@PathVariable String userID) {
        List<ProjectResponse> projects = projectService.getAllProjectsByUserId(userID);
        return ResponseEntity.ok(projects);
    }
}
