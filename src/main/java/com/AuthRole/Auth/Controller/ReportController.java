package com.AuthRole.Auth.Controller;

import com.AuthRole.Auth.Service.Interface.ReportService;
import com.AuthRole.Auth.model.DTO.ReportDto;
import com.AuthRole.Auth.model.Response.ReportResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<ReportResponse> createReport(@Validated @RequestBody ReportDto reportDto) {
        return ResponseEntity.ok(reportService.createReport(reportDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportResponse> updateReport(
            @PathVariable Long id,
            @Validated @RequestBody ReportDto reportDto) {
        return ResponseEntity.ok(reportService.updateReport(id, reportDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> getReportById(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getReportById(id));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ReportResponse>> getReportsByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(reportService.getReportsByProjectId(projectId));
    }
}
