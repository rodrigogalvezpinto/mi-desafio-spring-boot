package com.nuevospa.gestiontareas.api;

import com.nuevospa.gestiontareas.model.EstadoTarea;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstadosApiDelegate {
    ResponseEntity<List<EstadoTarea>> obtenerEstados();
} 