package com.AuthRole.Auth.model.Auth.Login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class LoginDto {


    @NotBlank(message = "Email is required and cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required and cannot be blank")
    private String password;



}
