package com.AuthRole.Auth.model.Auth.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String userID ;

    @NotBlank(message = "Username is required and cannot be blank")
    private String username;


    @NotBlank(message = "Email is required and cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required and cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;

    @NotBlank(message = "Address is required and cannot be blank")
    private String address;

    @NotBlank(message = "Password is required and cannot be blank")
    @Size(min = 6, message = "Minimum password length is 6 characters")
    private String password;
}
