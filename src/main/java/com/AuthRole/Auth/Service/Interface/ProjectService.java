package com.AuthRole.Auth.Service.Interface;


import com.AuthRole.Auth.model.DTO.ProjectDTO;
import com.AuthRole.Auth.model.Response.ProjectResponse;

import java.util.List;

public interface ProjectService {

    ProjectResponse createProject(ProjectDTO projectDTO , String createdBy);

    ProjectResponse updateProject(Long id, ProjectDTO projectDTO);

    void deleteProject(Long id);

    ProjectResponse getProjectById(Long id);

    List<ProjectResponse> getAllProjects();

    List<ProjectResponse> getAllProjectsByUserId(String userId);
}
