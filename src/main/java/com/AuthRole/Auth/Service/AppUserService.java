package com.AuthRole.Auth.Service;

import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class AppUserService implements UserDetailsService {


   private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new User(
                appUser.getEmail(),
                appUser.getPassword(),
                appUser.getUserRoles().stream()
                        .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getRoleName()))  // Map roles to authorities
                        .collect(Collectors.toList())
        );
    }
}
