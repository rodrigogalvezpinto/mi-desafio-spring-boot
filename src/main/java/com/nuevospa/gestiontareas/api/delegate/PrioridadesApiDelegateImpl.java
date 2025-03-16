package com.nuevospa.gestiontareas.api.delegate;

import com.nuevospa.gestiontareas.api.PrioridadesApiDelegate;
import com.nuevospa.gestiontareas.model.Prioridad;
import com.nuevospa.gestiontareas.repository.PrioridadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PrioridadesApiDelegateImpl implements PrioridadesApiDelegate {

    private final PrioridadRepository prioridadRepository;

    @Override
    public ResponseEntity<List<Prioridad>> obtenerPrioridades() {
        log.info("Obteniendo lista de prioridades");
        return ResponseEntity.ok(prioridadRepository.findAll());
    }
} 