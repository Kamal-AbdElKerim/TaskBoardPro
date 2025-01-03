package com.AuthRole.Auth.config;

import com.AuthRole.Auth.Service.AccountService;
import com.AuthRole.Auth.Service.Interface.IAccountService;
import com.AuthRole.Auth.model.Auth.Role.Role;
import com.AuthRole.Auth.model.Auth.UserRole;
import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.repository.RoleRepository;
import com.AuthRole.Auth.repository.UserRepository;
import com.AuthRole.Auth.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final IAccountService accountService;

    @Bean
    public CommandLineRunner loadData(RoleRepository roleRepository, UserRepository userRepository , UserRoleRepository userRoleRepository) {
        return args -> {
            // Check if roles already exist, if not, add them
            if (roleRepository.count() == 0) {
                Role adminRole = new Role();
                adminRole.setRoleName("ROLE_ADMIN");
                roleRepository.save(adminRole);

                Role userRole = new Role();
                userRole.setRoleName("ROLE_USER");
                roleRepository.save(userRole);
            }

            // Check if user exists, if not, add them
            if (userRepository.count() == 0) {
                var bCryptEncoder = new BCryptPasswordEncoder();

                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setEmail("admin@gmail.com");
                admin.setUserID(accountService.generateUserID(admin));
                admin.setPassword(bCryptEncoder.encode("admin"));
                userRepository.save(admin);

                // Assuming the 'adminRole' is the first role in the database
                Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN").orElseThrow();


                UserRole userRole = new UserRole();
                userRole.setRole(adminRole);
                userRole.setAppUser(admin);
                userRoleRepository.save(userRole);

                AppUser user = new AppUser();
                user.setUsername("user");
                user.setEmail("user@gmail.com");
                user.setUserID(accountService.generateUserID(user));
                user.setPassword(bCryptEncoder.encode("user"));
                userRepository.save(user);

                Role userrRole = roleRepository.findByRoleName("ROLE_USER").orElseThrow();


                UserRole user_Role = new UserRole();
                user_Role.setRole(userrRole);
                user_Role.setAppUser(user);
                userRoleRepository.save(user_Role);
            }
        };
    }
}
