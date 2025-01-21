package com.AuthRole.Auth.Service;

import com.AuthRole.Auth.Exception.EntityNotFoundException;
import com.AuthRole.Auth.Service.Interface.ReportService;
import com.AuthRole.Auth.model.DTO.ReportDto;
import com.AuthRole.Auth.model.MapStruct.ReportMapper;
import com.AuthRole.Auth.model.Report;
import com.AuthRole.Auth.model.Project;
import com.AuthRole.Auth.model.Response.ReportResponse;
import com.AuthRole.Auth.repository.ProjectRepository;
import com.AuthRole.Auth.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ProjectRepository projectRepository;
    private final ReportMapper reportMapper;

    public ReportServiceImpl(ReportRepository reportRepository,
                             ProjectRepository projectRepository,
                             ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.projectRepository = projectRepository;
        this.reportMapper = reportMapper;
    }

    @Override
    public ReportResponse createReport(ReportDto reportDto) {
        Project project = projectRepository.findById(reportDto.getProject())
                .orElseThrow(() -> new EntityNotFoundException("Project", "Project not found"));

        Report report = reportMapper.toEntity(reportDto);
        report.setProject(project);

        Report savedReport = reportRepository.save(report);
        return reportMapper.toResponse(savedReport);
    }

    @Override
    public ReportResponse updateReport(Long id, ReportDto reportDto) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report", "Report not found"));

        report.setTitle(reportDto.getTitle());
        report.setDescription(reportDto.getDescription());
        report.setGeneratedAt(reportDto.getGeneratedAt());
        report.setSuccessful(reportDto.getIsSuccessful());

        Report updatedReport = reportRepository.save(report);
        return reportMapper.toResponse(updatedReport);
    }

    @Override
    public void deleteReport(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report", "Report not found"));

        reportRepository.delete(report);
    }

    @Override
    public ReportResponse getReportById(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report", "Report not found"));

        return reportMapper.toResponse(report);
    }

    @Override
    public List<ReportResponse> getReportsByProjectId(Long projectId) {
        return reportRepository.findByProjectId(projectId).stream()
                .map(reportMapper::toResponse)
                .collect(Collectors.toList());
    }
}
