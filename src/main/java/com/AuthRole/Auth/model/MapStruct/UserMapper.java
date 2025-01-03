package com.AuthRole.Auth.model.MapStruct;



import com.AuthRole.Auth.model.Auth.Role.RoleResponseDto;
import com.AuthRole.Auth.model.Auth.UserRole;
import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.Auth.user.UserDto;
import com.AuthRole.Auth.model.Auth.user.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto AppUserToUserDto(AppUser user);
    @Mapping(target = "roles", expression = "java(mapRolesToRoleResponseDto(appUser.getUserRoles()))")
    UserResponseDto appUserToUserResponseDto(AppUser appUser);



    AppUser UserDtoToAppUser(UserDto userDto);

    default List<RoleResponseDto> mapRolesToRoleResponseDto(List<UserRole> userRoles) {
        return userRoles.stream()
                .map(userRole -> new RoleResponseDto( userRole.getRole().getRoleName()))
                .collect(java.util.stream.Collectors.toList());
    }
}
