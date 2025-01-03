package com.AuthRole.Auth.model.MapStruct;


import com.AuthRole.Auth.model.Auth.Role.Role;
import com.AuthRole.Auth.model.Auth.Role.RoleResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    // Mapping Role to RoleResponseDto
    RoleResponseDto roleToRoleResponseDto(Role role);
}
