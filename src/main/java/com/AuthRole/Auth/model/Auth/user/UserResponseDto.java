package com.AuthRole.Auth.model.Auth.user;


import com.AuthRole.Auth.model.Auth.Role.RoleResponseDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class UserResponseDto {
    private String userID;
    private String username;
    private String email;
    private String phone;
    private String address;
    private List<RoleResponseDto> roles;
}
