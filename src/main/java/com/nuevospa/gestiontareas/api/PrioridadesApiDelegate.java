package com.nuevospa.gestiontareas.api;

import com.nuevospa.gestiontareas.model.Prioridad;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PrioridadesApiDelegate {
    ResponseEntity<List<Prioridad>> obtenerPrioridades();
} 