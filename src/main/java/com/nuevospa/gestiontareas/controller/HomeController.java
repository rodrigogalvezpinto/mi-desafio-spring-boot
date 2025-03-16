package com.nuevospa.gestiontareas.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador simple que proporciona un endpoint para verificar el estado de la API.
 * 
 * Este controlador no forma parte del enfoque API First principal, sino que es un
 * endpoint de utilidad para verificar si la API está funcionando correctamente.
 * No está marcado como @Deprecated porque no duplica funcionalidad con el enfoque API First.
 */
@RestController
public class HomeController {

    /**
     * Endpoint para verificar el estado de la API.
     * 
     * @return Un objeto JSON con información sobre el estado de la API.
     */
    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> home() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "online");
        response.put("message", "API de Gestión de Tareas funcionando correctamente");
        return ResponseEntity.ok(response);
    }
} 