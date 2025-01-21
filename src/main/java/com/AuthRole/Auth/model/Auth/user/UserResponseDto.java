package com.AuthRole.Auth.model.Auth.user;


import com.AuthRole.Auth.model.Auth.Role.RoleResponseDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class UserResponseDto {
    private String id;
    private String username;
    private String email;
    private List<RoleResponseDto> roles;
}
