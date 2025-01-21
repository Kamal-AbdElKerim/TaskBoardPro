package com.AuthRole.Auth.Service;


import com.AuthRole.Auth.Service.Interface.KanbanColumnService;
import com.AuthRole.Auth.model.DTO.KanbanColumnDto;
import com.AuthRole.Auth.model.KanbanColumn;
import com.AuthRole.Auth.model.MapStruct.KanbanColumnMapper;
import com.AuthRole.Auth.model.Project;
import com.AuthRole.Auth.model.Response.KanbanColumnResponse;
import com.AuthRole.Auth.repository.KanbanColumnRepository;
import com.AuthRole.Auth.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KanbanColumnServiceImpl implements KanbanColumnService {

    private final KanbanColumnRepository kanbanColumnRepository;
    private final ProjectRepository projectRepository;
    private final KanbanColumnMapper kanbanColumnMapper;

    public KanbanColumnServiceImpl(KanbanColumnRepository kanbanColumnRepository,
                                   ProjectRepository projectRepository,
                                   KanbanColumnMapper kanbanColumnMapper) {
        this.kanbanColumnRepository = kanbanColumnRepository;
        this.projectRepository = projectRepository;
        this.kanbanColumnMapper = kanbanColumnMapper;
    }

    @Override
    public KanbanColumnResponse createKanbanColumn(KanbanColumnDto kanbanColumnDto) {
        Project project = projectRepository.findById(kanbanColumnDto.getProject().getId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        KanbanColumn kanbanColumn = kanbanColumnMapper.toEntity(kanbanColumnDto);
        kanbanColumn.setProject(project);

        KanbanColumn savedColumn = kanbanColumnRepository.save(kanbanColumn);
        return kanbanColumnMapper.toResponse(savedColumn);
    }

    @Override
    public KanbanColumnResponse updateKanbanColumn(Long id, KanbanColumnDto kanbanColumnDto) {
        KanbanColumn kanbanColumn = kanbanColumnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kanban column not found"));

        kanbanColumn.setName(kanbanColumnDto.getName());
        kanbanColumn.setPosition(kanbanColumnDto.getPosition());

        KanbanColumn updatedColumn = kanbanColumnRepository.save(kanbanColumn);
        return kanbanColumnMapper.toResponse(updatedColumn);
    }

    @Override
    public void deleteKanbanColumn(Long id) {
        KanbanColumn kanbanColumn = kanbanColumnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kanban column not found"));

        kanbanColumnRepository.delete(kanbanColumn);
    }

    @Override
    public KanbanColumnResponse getKanbanColumnById(Long id) {
        KanbanColumn kanbanColumn = kanbanColumnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kanban column not found"));

        return kanbanColumnMapper.toResponse(kanbanColumn);
    }

    @Override
    public List<KanbanColumnResponse> getAllKanbanColumns() {
        return kanbanColumnRepository.findAll().stream()
                .map(kanbanColumnMapper::toResponse)
                .collect(Collectors.toList());
    }
}

