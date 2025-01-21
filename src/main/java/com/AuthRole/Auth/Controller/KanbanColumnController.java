package com.AuthRole.Auth.Controller;

import com.AuthRole.Auth.Service.Interface.KanbanColumnService;
import com.AuthRole.Auth.model.DTO.KanbanColumnDto;
import com.AuthRole.Auth.model.Response.KanbanColumnResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kanban-columns")
public class KanbanColumnController {

    private final KanbanColumnService kanbanColumnService;

    public KanbanColumnController(KanbanColumnService kanbanColumnService) {
        this.kanbanColumnService = kanbanColumnService;
    }

    // Create a new Kanban column
    @PostMapping
    public ResponseEntity<KanbanColumnResponse> createKanbanColumn(@Valid @RequestBody KanbanColumnDto kanbanColumnDto) {
        KanbanColumnResponse response = kanbanColumnService.createKanbanColumn(kanbanColumnDto);
        return ResponseEntity.ok(response);
    }

    // Update an existing Kanban column
    @PutMapping("/{id}")
    public ResponseEntity<KanbanColumnResponse> updateKanbanColumn(
            @PathVariable Long id,
            @Valid @RequestBody KanbanColumnDto kanbanColumnDto) {
        KanbanColumnResponse response = kanbanColumnService.updateKanbanColumn(id, kanbanColumnDto);
        return ResponseEntity.ok(response);
    }

    // Delete a Kanban column by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKanbanColumn(@PathVariable Long id) {
        kanbanColumnService.deleteKanbanColumn(id);
        return ResponseEntity.noContent().build();
    }

    // Retrieve a single Kanban column by ID
    @GetMapping("/{id}")
    public ResponseEntity<KanbanColumnResponse> getKanbanColumnById(@PathVariable Long id) {
        KanbanColumnResponse response = kanbanColumnService.getKanbanColumnById(id);
        return ResponseEntity.ok(response);
    }

    // Retrieve all Kanban columns
    @GetMapping
    public ResponseEntity<List<KanbanColumnResponse>> getAllKanbanColumns() {
        List<KanbanColumnResponse> responseList = kanbanColumnService.getAllKanbanColumns();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<KanbanColumnResponse>> getAllKanbanColumnsByProjectId(@PathVariable Long projectId) {
        List<KanbanColumnResponse> responseList = kanbanColumnService.getKanbanColumnsByProjectId(projectId);
        return ResponseEntity.ok(responseList);
    }

}
