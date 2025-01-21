package com.AuthRole.Auth.repository;

import com.AuthRole.Auth.model.KanbanColumn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanbanColumnRepository extends JpaRepository<KanbanColumn, Long> {
}
