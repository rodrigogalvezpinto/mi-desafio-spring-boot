package com.nuevospa.gestiontareas.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService jwtUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        log.info("Request URI: " + request.getRequestURI() + ", Auth Header: " + (requestTokenHeader != null ? "present" : "null"));

        String username = null;
        String jwtToken = null;

        // JWT Token está en el formato "Bearer token". Eliminar Bearer y obtener solo el token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
            // Extraer el token después de "Bearer" y eliminar espacios en blanco
            jwtToken = requestTokenHeader.substring(6).trim();
            log.info("Token extraído: " + (jwtToken.length() > 10 ? jwtToken.substring(0, 10) + "..." : jwtToken));
            
            try {
                username = jwtTokenUtil.extractUsername(jwtToken);
                log.info("Token processed successfully for username: " + username);
            } catch (Exception e) {
                log.error("JWT Token inválido o expirado: " + e.getMessage());
            }
        } else {
            log.debug("JWT Token no comienza con Bearer o es nulo");
        }

        // Una vez que tenemos el token, validamos
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            // Si el token es válido, configuramos Spring Security
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                log.info("Usuario autenticado: " + username + ", roles: " + userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                log.warn("Token no válido para el usuario: " + username);
            }
        }
        chain.doFilter(request, response);
    }
} 