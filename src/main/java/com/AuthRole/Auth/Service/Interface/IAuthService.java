package com.AuthRole.Auth.Service.Interface;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface  IAuthService {

    ResponseEntity<Object> AuthInfo(Authentication auth);

}
