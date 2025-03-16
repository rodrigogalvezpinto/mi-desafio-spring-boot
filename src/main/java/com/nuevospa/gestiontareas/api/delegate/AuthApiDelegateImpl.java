package com.nuevospa.gestiontareas.api.delegate;

import com.nuevospa.gestiontareas.api.AuthApiDelegate;
import com.nuevospa.gestiontareas.dto.LoginRequest;
import com.nuevospa.gestiontareas.dto.LoginResponse;
import com.nuevospa.gestiontareas.model.Usuario;
import com.nuevospa.gestiontareas.repository.UsuarioRepository;
import com.nuevospa.gestiontareas.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthApiDelegateImpl implements AuthApiDelegate {

    private final JwtTokenUtil jwtTokenUtil;
    private final UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        log.info("Procesando solicitud de login para: {}", loginRequest.getEmail());
        
        // Verificación simplificada - solo comprobamos que el email exista
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado: {}", loginRequest.getEmail());
                    return new RuntimeException("Usuario no encontrado");
                });
        
        // Crear UserDetails manualmente
        UserDetails userDetails = User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .authorities(Collections.emptyList())
                .build();
        
        // Generar token JWT
        String token = jwtTokenUtil.generateToken(userDetails);
        log.info("Token JWT generado para: {}", loginRequest.getEmail());
        
        // Construir respuesta
        LoginResponse response = LoginResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .usuarioId(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .build();
        
        return ResponseEntity.ok(response);
    }
} 