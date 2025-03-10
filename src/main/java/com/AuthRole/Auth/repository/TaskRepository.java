package com.AuthRole.Auth.repository;


import com.AuthRole.Auth.model.KanbanColumn;
import com.AuthRole.Auth.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByKanbanColumnOrderByOrder(KanbanColumn kanbanColumn);
}
