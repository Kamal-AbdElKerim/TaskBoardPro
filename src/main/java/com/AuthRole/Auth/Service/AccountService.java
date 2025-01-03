package com.AuthRole.Auth.Service;

import com.AuthRole.Auth.Exception.EntityAlreadyExistsException;
import com.AuthRole.Auth.Exception.EntityNotFoundException;
import com.AuthRole.Auth.Service.Interface.IAccountService;
import com.AuthRole.Auth.config.SecurityConfig;
import com.AuthRole.Auth.model.Auth.Login.LoginDto;
import com.AuthRole.Auth.model.Auth.Role.Role;
import com.AuthRole.Auth.model.Auth.UserRole;
import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.Auth.user.UserDto;
import com.AuthRole.Auth.model.Auth.user.UserResponseDto;
import com.AuthRole.Auth.model.MapStruct.UserMapper;
import com.AuthRole.Auth.repository.RoleRepository;
import com.AuthRole.Auth.repository.UserRepository;
import com.AuthRole.Auth.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;


@RequiredArgsConstructor
@Service
public class AccountService implements IAccountService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfiguration;
    private final UserMapper userMapper;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<Object> Login(LoginDto loginDto){
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // You can access the roles here
        for (GrantedAuthority authority : authorities) {
            System.out.println("Role: " + authority.getAuthority());
        }

        AppUser user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User", "User not found with email: " + loginDto.getEmail()));
        return generateJwtTokenResponse(user);

    }

    @Override
    public ResponseEntity<Object> createUser(UserDto userDto){
        var bCryptEncoder = new BCryptPasswordEncoder();

        AppUser appuser = userMapper.UserDtoToAppUser(userDto);


        Optional<AppUser> otherUser = userRepository.findByEmail(userDto.getEmail());
        if (otherUser.isPresent()) {
            throw new EntityAlreadyExistsException("Email", "Email address already used.");
        }

        Role ROLE_USER = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found!"));

        appuser.setUserID(generateUserID(appuser));
        appuser.setPassword(bCryptEncoder.encode(userDto.getPassword()));

        // Initialize userRoles list if it's not initialized
        if (appuser.getUserRoles() == null) {
            appuser.setUserRoles(new ArrayList<>());
        }

        // Create and add the UserRole to the user's userRoles list
        UserRole userRole = UserRole.builder()
                .appUser(appuser)
                .role(ROLE_USER)
                .build();

        appuser.getUserRoles().add(userRole);

        // Save the new user and the UserRole association
        userRepository.save(appuser);
        userRoleRepository.save(userRole);

        return generateJwtTokenResponse(appuser);
    }


    private ResponseEntity<Object> generateJwtTokenResponse(AppUser appUser) {
        // Generate JWT token
        String jwtToken = securityConfiguration.createJwtToken(appUser);

        // Map AppUser to UserResponseDto
        UserResponseDto userResponseDto = userMapper.appUserToUserResponseDto(appUser);

        // Prepare response
        var response = new HashMap<String, Object>();
        response.put("token", jwtToken);
        response.put("user", userResponseDto);

        return ResponseEntity.ok(response);
    }

    @Override
    public String generateUserID(AppUser appUser) {
        // For example, you can combine the username and UUID to generate a userID
        return UUID.randomUUID().toString().substring(0, 10) + appUser.getEmail().toLowerCase()  + UUID.randomUUID().toString().substring(0, 10);
    }

}
