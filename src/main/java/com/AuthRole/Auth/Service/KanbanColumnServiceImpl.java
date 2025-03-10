package com.AuthRole.Auth.Service;


import com.AuthRole.Auth.Exception.EntityNotFoundException;
import com.AuthRole.Auth.Service.Interface.KanbanColumnService;
import com.AuthRole.Auth.model.DTO.KanbanColumnDto;
import com.AuthRole.Auth.model.KanbanColumn;
import com.AuthRole.Auth.model.MapStruct.KanbanColumnMapper;
import com.AuthRole.Auth.model.Project;
import com.AuthRole.Auth.model.Response.KanbanColumnResponse;
import com.AuthRole.Auth.repository.KanbanColumnRepository;
import com.AuthRole.Auth.repository.ProjectRepository;
import com.AuthRole.Auth.repository.TaskRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KanbanColumnServiceImpl implements KanbanColumnService {

    private final KanbanColumnRepository kanbanColumnRepository;
    private final ProjectRepository projectRepository;
    private final KanbanColumnMapper kanbanColumnMapper;
    private final TaskRepository taskRepository;

    public KanbanColumnServiceImpl(KanbanColumnRepository kanbanColumnRepository,
                                   ProjectRepository projectRepository,
                                   KanbanColumnMapper kanbanColumnMapper, TaskRepository taskRepository) {
        this.kanbanColumnRepository = kanbanColumnRepository;
        this.projectRepository = projectRepository;
        this.kanbanColumnMapper = kanbanColumnMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public KanbanColumnResponse createKanbanColumn(KanbanColumnDto kanbanColumnDto) {
        Project project = projectRepository.findById(kanbanColumnDto.getProjectID())
                .orElseThrow(() -> new EntityNotFoundException("Project","Project not found"));

        KanbanColumn kanbanColumn = kanbanColumnMapper.toEntity(kanbanColumnDto);
        kanbanColumn.setProject(project);

        KanbanColumn savedColumn = kanbanColumnRepository.save(kanbanColumn);
        return kanbanColumnMapper.toResponse(savedColumn);
    }

    @Override
    public KanbanColumnResponse updateKanbanColumn(Long id, String kanbanColumnDto) {
        KanbanColumn kanbanColumn = kanbanColumnRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kanban","Kanban column not found"));

        kanbanColumn.setName(kanbanColumnDto);

        KanbanColumn updatedColumn = kanbanColumnRepository.save(kanbanColumn);
        return kanbanColumnMapper.toResponse(updatedColumn);
    }

    @Override
    public void deleteKanbanColumn(Long id) {
        KanbanColumn kanbanColumn = kanbanColumnRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kanban","Kanban column not found"));


        // Now delete the Kanban column
        kanbanColumnRepository.delete(kanbanColumn);
    }



    @Override
    public KanbanColumnResponse getKanbanColumnById(Long id) {
        KanbanColumn kanbanColumn = kanbanColumnRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kanban","Kanban column not found"));

        return kanbanColumnMapper.toResponse(kanbanColumn);
    }

    @Override
    public List<KanbanColumnResponse> getAllKanbanColumns() {
        return kanbanColumnRepository.findAll().stream()
                .map(kanbanColumnMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<KanbanColumnResponse> getKanbanColumnsByProjectId(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project","Project not found"));

        return kanbanColumnRepository.findByProject(project).stream()
                .map(kanbanColumnMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void updateColumnPositions(List<KanbanColumnDto> kanbanColumns) {
        List<KanbanColumn> updatedColumns = kanbanColumns.stream()
                .map(dto -> {
                    KanbanColumn column = kanbanColumnRepository.findById(dto.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Kanban", "Kanban column not found"));
                    column.setPosition(dto.getPosition());
                    return column;
                })
                .collect(Collectors.toList());

        kanbanColumnRepository.saveAll(updatedColumns);
    }


}

