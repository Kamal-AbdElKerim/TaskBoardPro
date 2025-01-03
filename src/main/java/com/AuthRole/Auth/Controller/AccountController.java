package com.AuthRole.Auth.Controller;


import com.AuthRole.Auth.Service.Interface.IAccountService;
import com.AuthRole.Auth.Service.Interface.IAuthService;
import com.AuthRole.Auth.model.Auth.Login.LoginDto;
import com.AuthRole.Auth.model.Auth.user.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final IAccountService accountService;
    private final IAuthService authService;

    @GetMapping("/profile")
    public ResponseEntity<Object> profile(Authentication authentication) {
       return authService.AuthInfo(authentication);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @Valid @RequestBody LoginDto loginDto
           ) {
        return accountService.Login(loginDto) ;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @Valid @RequestBody UserDto userDto
           ) {
        return accountService.createUser(userDto) ;
    }


}
