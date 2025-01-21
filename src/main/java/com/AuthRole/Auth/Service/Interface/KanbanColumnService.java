package com.AuthRole.Auth.Service.Interface;


import com.AuthRole.Auth.model.DTO.KanbanColumnDto;
import com.AuthRole.Auth.model.Response.KanbanColumnResponse;

import java.util.List;

public interface KanbanColumnService {

    KanbanColumnResponse createKanbanColumn(KanbanColumnDto kanbanColumnDto);

    KanbanColumnResponse updateKanbanColumn(Long id, KanbanColumnDto kanbanColumnDto);

    void deleteKanbanColumn(Long id);

    KanbanColumnResponse getKanbanColumnById(Long id);

    List<KanbanColumnResponse> getAllKanbanColumns();

    List<KanbanColumnResponse> getKanbanColumnsByProjectId(Long projectId);

}
