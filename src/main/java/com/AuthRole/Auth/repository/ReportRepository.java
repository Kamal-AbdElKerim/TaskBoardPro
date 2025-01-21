package com.AuthRole.Auth.repository;

import com.AuthRole.Auth.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByProjectId(Long projectId);  // Custom query method to get reports by project ID
}
