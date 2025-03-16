package com.nuevospa.gestiontareas.dto;

import com.nuevospa.gestiontareas.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String tokenType;
    private UUID usuarioId;
    private String nombre;
    private String email;
    private Rol rol;
} 