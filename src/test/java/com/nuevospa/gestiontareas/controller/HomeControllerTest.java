package com.nuevospa.gestiontareas.controller;

import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Test
    void home_DebeRetornarEstadoOnline_CuandoSeInvoca() {
        // Given
        // No se requiere configuración previa

        // When
        ResponseEntity<Map<String, String>> response = homeController.home();

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
        then(response.getBody().get("status")).isEqualTo("online");
        then(response.getBody().get("message")).isEqualTo("API de Gestión de Tareas funcionando correctamente");
    }
} 