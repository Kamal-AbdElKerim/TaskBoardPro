package com.AuthRole.Auth.model.MapStruct;

import com.AuthRole.Auth.model.DTO.ReportDto;
import com.AuthRole.Auth.model.Report;
import com.AuthRole.Auth.model.Response.ReportResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(source = "project", target = "project.id")  // Map project ID to project in ReportDto
    Report toEntity(ReportDto dto);

    @Mapping(source = "project.id", target = "project")  // Map project ID to project in ReportResponse
    ReportResponse toResponse(Report report);
}
