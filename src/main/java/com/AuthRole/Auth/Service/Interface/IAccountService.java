package com.AuthRole.Auth.Service.Interface;


import com.AuthRole.Auth.model.Auth.Login.LoginDto;
import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.AuthRole.Auth.model.Auth.user.UserDto;
import org.springframework.http.ResponseEntity;

public interface IAccountService {

    ResponseEntity<Object> Login(LoginDto loginDto);

    ResponseEntity<Object> createUser(UserDto userDto);

    String generateUserID(AppUser appUser);

}
