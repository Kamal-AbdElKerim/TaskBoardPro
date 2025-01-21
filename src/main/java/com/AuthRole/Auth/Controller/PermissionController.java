package com.AuthRole.Auth.Controller;

import com.AuthRole.Auth.Service.Interface.PermissionService;
import com.AuthRole.Auth.model.DTO.PermissionDto;
import com.AuthRole.Auth.model.Response.PermissionResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<PermissionResponse> createPermission(@Valid @RequestBody PermissionDto permissionDto) {
        return ResponseEntity.ok(permissionService.createPermission(permissionDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponse> updatePermission(
            @PathVariable Long id,
            @Validated @RequestBody PermissionDto permissionDto) {
        return ResponseEntity.ok(permissionService.updatePermission(id, permissionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponse> getPermissionById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<PermissionResponse>> getPermissionsByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(permissionService.getPermissionsByProjectId(projectId));
    }
}
