package com.nuevospa.gestiontareas.api;

import org.springframework.http.ResponseEntity;

import com.nuevospa.gestiontareas.dto.LoginRequest;
import com.nuevospa.gestiontareas.dto.LoginResponse;

public interface AuthApiDelegate {
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest);
} 