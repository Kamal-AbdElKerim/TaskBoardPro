package com.AuthRole.Auth.Service;

import com.AuthRole.Auth.Exception.EntityNotFoundException;
import com.AuthRole.Auth.Service.Interface.IAuthService;
import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.Auth.user.UserResponseDto;
import com.AuthRole.Auth.model.MapStruct.UserMapper;
import com.AuthRole.Auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<Object> AuthInfo(Authentication auth) {

        Jwt jwt = (Jwt) auth.getPrincipal();


        String email = jwt.getClaimAsString("email");

        if (email == null) {
            throw new EntityNotFoundException("appUser", "appUser with email " + email + " not found");
        }

        // Fetch the user by email
        AppUser appUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("appUser", "appUser with email " + email + " not found"));

        System.out.println(appUser.getAddress());
        // Map the AppUser to UserResponseDto
        UserResponseDto userResponseDto = userMapper.appUserToUserResponseDto(appUser);
        return ResponseEntity.ok(userResponseDto);
    }


}
