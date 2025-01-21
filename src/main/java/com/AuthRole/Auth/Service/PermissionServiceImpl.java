package com.AuthRole.Auth.Service;

import com.AuthRole.Auth.Exception.EntityNotFoundException;
import com.AuthRole.Auth.Service.Interface.PermissionService;
import com.AuthRole.Auth.model.DTO.PermissionDto;
import com.AuthRole.Auth.model.MapStruct.PermissionMapper;
import com.AuthRole.Auth.model.Permission;
import com.AuthRole.Auth.model.Project;
import com.AuthRole.Auth.model.Response.PermissionResponse;
import com.AuthRole.Auth.repository.PermissionRepository;
import com.AuthRole.Auth.repository.ProjectRepository;
import com.AuthRole.Auth.repository.UserRepository;  // Add UserRepository to fetch AppUser
import com.AuthRole.Auth.model.Auth.user.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final ProjectRepository projectRepository;
    private final PermissionMapper permissionMapper;
    private final UserRepository userRepository;  // Add UserRepository

    // Constructor injection for UserRepository
    public PermissionServiceImpl(PermissionRepository permissionRepository,
                                 ProjectRepository projectRepository,
                                 PermissionMapper permissionMapper,
                                 UserRepository userRepository) {
        this.permissionRepository = permissionRepository;
        this.projectRepository = projectRepository;
        this.permissionMapper = permissionMapper;
        this.userRepository = userRepository;  // Initialize UserRepository
    }

    @Override
    public PermissionResponse createPermission(PermissionDto permissionDto) {
        // Fetch the Project by its ID
        Project project = projectRepository.findById(permissionDto.getProjectID())
                .orElseThrow(() -> new EntityNotFoundException("Project", "Project not found"));

        // Fetch the User by its ID
        AppUser createdByUser = userRepository.findById(permissionDto.getUserID())
                .orElseThrow(() -> new EntityNotFoundException("User", "User not found"));

        // Map PermissionDto to Permission entity and set the Project and User
        Permission permission = permissionMapper.toEntity(permissionDto);
        permission.setProject(project);
        permission.setUser(createdByUser);  // Set the User

        // Save the Permission entity
        Permission savedPermission = permissionRepository.save(permission);
        return permissionMapper.toResponse(savedPermission);
    }

    @Override
    public PermissionResponse updatePermission(Long id, PermissionDto permissionDto) {
        // Fetch the existing Permission
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission", "Permission not found"));

        // Fetch the User by its ID
        AppUser createdByUser = userRepository.findById(permissionDto.getUserID())
                .orElseThrow(() -> new EntityNotFoundException("User", "User not found"));

        // Update the Permission details
        permission.setPermissionType(permissionDto.getPermissionType());
        permission.setUser(createdByUser);  // Update the User

        // Save the updated Permission entity
        Permission updatedPermission = permissionRepository.save(permission);
        return permissionMapper.toResponse(updatedPermission);
    }

    @Override
    public void deletePermission(Long id) {
        // Fetch the Permission
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission", "Permission not found"));

        // Delete the Permission entity
        permissionRepository.delete(permission);
    }

    @Override
    public PermissionResponse getPermissionById(Long id) {
        // Fetch the Permission by its ID
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission", "Permission not found"));

        // Map the Permission entity to PermissionResponse
        return permissionMapper.toResponse(permission);
    }

    @Override
    public List<PermissionResponse> getPermissionsByProjectId(Long projectId) {
        // Fetch Permissions by project ID and map them to PermissionResponse
        return permissionRepository.findByProjectId(projectId).stream()
                .map(permissionMapper::toResponse)
                .collect(Collectors.toList());
    }
}
