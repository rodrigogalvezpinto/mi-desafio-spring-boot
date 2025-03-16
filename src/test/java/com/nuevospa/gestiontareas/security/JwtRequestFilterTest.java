package com.nuevospa.gestiontareas.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
class JwtRequestFilterTest {

    @Mock
    private UserDetailsService jwtUserDetailsService;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtRequestFilter jwtRequestFilter;

    private UserDetails userDetails;
    private String token;

    @BeforeEach
    void setUp() {
        // Limpiar el contexto de seguridad antes de cada prueba
        SecurityContextHolder.clearContext();
        
        // Crear UserDetails para las pruebas
        userDetails = User.builder()
                .username("usuario@test.com")
                .password("password")
                .authorities(Collections.emptyList())
                .build();
        
        token = "validToken";
    }

    @Test
    void doFilterInternal_DebeAutenticarUsuario_CuandoTokenEsValido(CapturedOutput capturedOutput) throws ServletException, IOException {
        // Given
        given(request.getHeader("Authorization")).willReturn("Bearer " + token);
        given(request.getRequestURI()).willReturn("/api/tareas");
        given(jwtTokenUtil.extractUsername(anyString())).willReturn("usuario@test.com");
        given(jwtUserDetailsService.loadUserByUsername(anyString())).willReturn(userDetails);
        given(jwtTokenUtil.validateToken(anyString(), any(UserDetails.class))).willReturn(true);

        // When
        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        // Then
        then(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
        then(SecurityContextHolder.getContext().getAuthentication().getName()).isEqualTo("usuario@test.com");
        then(capturedOutput).contains("Token processed successfully for username: usuario@test.com");
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_NoDebeAutenticarUsuario_CuandoNoHayToken(CapturedOutput capturedOutput) throws ServletException, IOException {
        // Given
        given(request.getHeader("Authorization")).willReturn(null);
        given(request.getRequestURI()).willReturn("/api/tareas");

        // When
        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        // Then
        then(SecurityContextHolder.getContext().getAuthentication()).isNull();
        then(capturedOutput).contains("Auth Header: null");
        verify(jwtTokenUtil, never()).extractUsername(anyString());
        verify(jwtUserDetailsService, never()).loadUserByUsername(anyString());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_NoDebeAutenticarUsuario_CuandoTokenNoEsValido(CapturedOutput capturedOutput) throws ServletException, IOException {
        // Given
        given(request.getHeader("Authorization")).willReturn("Bearer " + token);
        given(request.getRequestURI()).willReturn("/api/tareas");
        given(jwtTokenUtil.extractUsername(anyString())).willThrow(new RuntimeException("Token inválido"));

        // When
        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        // Then
        then(SecurityContextHolder.getContext().getAuthentication()).isNull();
        then(capturedOutput).contains("JWT Token inválido o expirado: Token inválido");
        verify(jwtUserDetailsService, never()).loadUserByUsername(anyString());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_NoDebeAutenticarUsuario_CuandoTokenNoEsValidoParaUsuario(CapturedOutput capturedOutput) throws ServletException, IOException {
        // Given
        given(request.getHeader("Authorization")).willReturn("Bearer " + token);
        given(request.getRequestURI()).willReturn("/api/tareas");
        given(jwtTokenUtil.extractUsername(anyString())).willReturn("usuario@test.com");
        given(jwtUserDetailsService.loadUserByUsername(anyString())).willReturn(userDetails);
        given(jwtTokenUtil.validateToken(anyString(), any(UserDetails.class))).willReturn(false);

        // When
        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        // Then
        then(SecurityContextHolder.getContext().getAuthentication()).isNull();
        verify(filterChain).doFilter(request, response);
    }
} 