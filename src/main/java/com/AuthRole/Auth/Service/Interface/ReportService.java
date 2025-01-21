package com.AuthRole.Auth.Service.Interface;

import com.AuthRole.Auth.model.DTO.ReportDto;
import com.AuthRole.Auth.model.Response.ReportResponse;

import java.util.List;

public interface ReportService {
    ReportResponse createReport(ReportDto reportDto);
    ReportResponse updateReport(Long id, ReportDto reportDto);
    void deleteReport(Long id);
    ReportResponse getReportById(Long id);
    List<ReportResponse> getReportsByProjectId(Long projectId);
}
