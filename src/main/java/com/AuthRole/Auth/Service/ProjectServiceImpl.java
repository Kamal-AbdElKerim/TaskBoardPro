package com.AuthRole.Auth.Service;

import com.AuthRole.Auth.Exception.EntityAlreadyExistsException;
import com.AuthRole.Auth.Exception.EntityNotFoundException;
import com.AuthRole.Auth.Service.Interface.KanbanColumnService;
import com.AuthRole.Auth.Service.Interface.ProjectService;
import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.DTO.ProjectDTO;
import com.AuthRole.Auth.model.KanbanColumn;
import com.AuthRole.Auth.model.MapStruct.ProjectMapper;
import com.AuthRole.Auth.model.Project;
import com.AuthRole.Auth.model.Response.ProjectResponse;
import com.AuthRole.Auth.repository.KanbanColumnRepository;
import com.AuthRole.Auth.repository.ProjectRepository;
import com.AuthRole.Auth.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;
    private final KanbanColumnRepository kanbanColumnRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, ProjectMapper projectMapper , KanbanColumnRepository kanbanColumnService) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectMapper = projectMapper;
        this.kanbanColumnRepository = kanbanColumnService;
    }

    @Override
    public ProjectResponse createProject(ProjectDTO projectDTO, String createdBy) {

        Optional<Project> existingProject = projectRepository.findByName(projectDTO.getName());

        if (existingProject.isPresent()) {
            throw new EntityAlreadyExistsException("Project", "A project with the name '" + projectDTO.getName() + "' already exists.");
        }

        AppUser createdByUser = userRepository.findById(createdBy)
                .orElseThrow(() -> new EntityNotFoundException("User", "User not found"));



        Project project = projectMapper.toEntity(projectDTO);
        project.setCreatedBy(createdByUser);
        project.setCreatedAt(LocalDateTime.now());

        // Save the project first
        Project savedProject = projectRepository.save(project);

        // Create default Kanban columns
        List<KanbanColumn> defaultColumns = List.of(
                createKanbanColumn("TODO", 0, savedProject),
                createKanbanColumn("IN_PROGRESS", 1, savedProject),
                createKanbanColumn("DONE", 2, savedProject)
        );

        // Save the Kanban columns
        List<KanbanColumn> savedColumns = kanbanColumnRepository.saveAll(defaultColumns);

        // Associate saved Kanban columns with the project
        savedProject.setKanbanColumns(savedColumns);

        // Save the project again to update the relationship
        projectRepository.save(savedProject);

        // Map to ProjectResponse and return
        return projectMapper.toResponse(savedProject);
    }

    private KanbanColumn createKanbanColumn(String name, int position, Project project) {
        KanbanColumn column = new KanbanColumn();
        column.setName(name);
        column.setPosition(position);
        column.setProject(project);
        column.setTasks(new ArrayList<>());
        return column;
    }


    @Override
    public ProjectResponse updateProject(Long id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project","Project not found"));
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        Project updatedProject = projectRepository.save(project);
        return projectMapper.toResponse(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project","Project not found"));

        projectRepository.delete(project);
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project","Project not found"));

        return projectMapper.toResponse(project);
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectResponse> getAllProjectsByUserId(String userId) {
        AppUser createdBy = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User" ,"User not found"));

        return projectRepository.findByCreatedBy(createdBy).stream()
                .map(projectMapper::toResponse)
                .collect(Collectors.toList());
    }
}
