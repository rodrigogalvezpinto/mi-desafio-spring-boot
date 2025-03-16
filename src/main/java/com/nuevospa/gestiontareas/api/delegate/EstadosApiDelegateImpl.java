package com.nuevospa.gestiontareas.api.delegate;

import com.nuevospa.gestiontareas.api.EstadosApiDelegate;
import com.nuevospa.gestiontareas.model.EstadoTarea;
import com.nuevospa.gestiontareas.repository.EstadoTareaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EstadosApiDelegateImpl implements EstadosApiDelegate {

    private final EstadoTareaRepository estadoTareaRepository;

    @Override
    public ResponseEntity<List<EstadoTarea>> obtenerEstados() {
        log.info("Obteniendo lista de estados de tarea");
        List<EstadoTarea> estados = estadoTareaRepository.findAll();
        return ResponseEntity.ok(estados);
    }
} 