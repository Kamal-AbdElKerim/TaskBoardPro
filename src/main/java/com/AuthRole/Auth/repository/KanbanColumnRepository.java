package com.AuthRole.Auth.repository;

import com.AuthRole.Auth.model.KanbanColumn;
import com.AuthRole.Auth.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KanbanColumnRepository extends JpaRepository<KanbanColumn, Long> {
    List<KanbanColumn> findByProject(Project project);

}
