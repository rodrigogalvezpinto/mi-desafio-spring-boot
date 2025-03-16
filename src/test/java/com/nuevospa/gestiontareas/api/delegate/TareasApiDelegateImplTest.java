package com.nuevospa.gestiontareas.api.delegate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nuevospa.gestiontareas.dto.CambioEstadoTarea;
import com.nuevospa.gestiontareas.dto.PageTarea;
import com.nuevospa.gestiontareas.model.Tarea;
import com.nuevospa.gestiontareas.service.TareaService;

@ExtendWith(MockitoExtension.class)
class TareasApiDelegateImplTest {

    @Mock
    private TareaService tareaService;

    @InjectMocks
    private TareasApiDelegateImpl tareasApiDelegate;

    private UUID tareaId;
    private Tarea tarea;
    private CambioEstadoTarea cambioEstadoTarea;
    private List<Tarea> tareasList;
    private Page<Tarea> tareasPage;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        tareaId = UUID.randomUUID();
        
        tarea = new Tarea();
        tarea.setId(tareaId);
        tarea.setTitulo("Tarea de prueba");
        tarea.setDescripcion("Descripción de prueba");
        tarea.setEstadoId(1);
        tarea.setPrioridadId(1);
        tarea.setFechaCreacion(LocalDateTime.now());
        
        cambioEstadoTarea = new CambioEstadoTarea();
        cambioEstadoTarea.setEstadoId(2);
        
        tareasList = Arrays.asList(tarea);
        pageable = PageRequest.of(0, 10);
        tareasPage = new PageImpl<>(tareasList, pageable, tareasList.size());
    }

    @Test
    void obtenerTareas_DebeRetornarPageTarea_CuandoSeInvocaConFiltros() {
        // Given
        given(tareaService.findFiltered(any(), any(), any(), any(Pageable.class)))
                .willReturn(tareasPage);

        // When
        ResponseEntity<PageTarea> response = tareasApiDelegate.obtenerTareas(0, 10, 1, null, 1, "fechaCreacion,desc");

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
        then(response.getBody().getContent()).hasSize(1);
        then(response.getBody().getContent().get(0).getId()).isEqualTo(tareaId);
        verify(tareaService).findFiltered(eq(1), eq(null), eq(1), any(Pageable.class));
    }

    @Test
    void obtenerTareaPorId_DebeRetornarTarea_CuandoExisteTarea() {
        // Given
        given(tareaService.findById(any(UUID.class)))
                .willReturn(Optional.of(tarea));

        // When
        ResponseEntity<Tarea> response = tareasApiDelegate.obtenerTareaPorId(tareaId);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
        then(response.getBody().getId()).isEqualTo(tareaId);
        then(response.getBody().getTitulo()).isEqualTo("Tarea de prueba");
        verify(tareaService).findById(eq(tareaId));
    }

    @Test
    void obtenerTareaPorId_DebeRetornarNotFound_CuandoNoExisteTarea() {
        // Given
        given(tareaService.findById(any(UUID.class)))
                .willReturn(Optional.empty());

        // When
        ResponseEntity<Tarea> response = tareasApiDelegate.obtenerTareaPorId(tareaId);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        then(response.getBody()).isNull();
        verify(tareaService).findById(eq(tareaId));
    }

    @Test
    void crearTarea_DebeRetornarCreated_CuandoTareaEsValida() {
        // Given
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContextPath("/api");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        given(tareaService.save(any(Tarea.class)))
                .willReturn(tarea);

        // When
        ResponseEntity<Void> response = tareasApiDelegate.crearTarea(tarea);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        then(response.getHeaders().getLocation()).isNotNull();
        verify(tareaService).save(eq(tarea));
        
        // Limpiar el contexto
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void actualizarTarea_DebeRetornarTareaActualizada_CuandoExisteTarea() {
        // Given
        given(tareaService.findById(any(UUID.class)))
                .willReturn(Optional.of(tarea));
        given(tareaService.save(any(Tarea.class)))
                .willReturn(tarea);

        // When
        ResponseEntity<Tarea> response = tareasApiDelegate.actualizarTarea(tareaId, tarea);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
        then(response.getBody().getId()).isEqualTo(tareaId);
        verify(tareaService).findById(eq(tareaId));
        verify(tareaService).save(eq(tarea));
    }

    @Test
    void actualizarTarea_DebeRetornarNotFound_CuandoNoExisteTarea() {
        // Given
        given(tareaService.findById(any(UUID.class)))
                .willReturn(Optional.empty());

        // When
        ResponseEntity<Tarea> response = tareasApiDelegate.actualizarTarea(tareaId, tarea);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        then(response.getBody()).isNull();
        verify(tareaService).findById(eq(tareaId));
    }

    @Test
    void eliminarTarea_DebeRetornarNoContent_CuandoExisteTarea() {
        // Given
        given(tareaService.findById(any(UUID.class)))
                .willReturn(Optional.of(tarea));

        // When
        ResponseEntity<Void> response = tareasApiDelegate.eliminarTarea(tareaId);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(tareaService).findById(eq(tareaId));
        verify(tareaService).deleteById(eq(tareaId));
    }

    @Test
    void eliminarTarea_DebeRetornarNotFound_CuandoNoExisteTarea() {
        // Given
        given(tareaService.findById(any(UUID.class)))
                .willReturn(Optional.empty());

        // When
        ResponseEntity<Void> response = tareasApiDelegate.eliminarTarea(tareaId);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(tareaService).findById(eq(tareaId));
    }

    @Test
    void actualizarEstadoTarea_DebeRetornarTareaActualizada_CuandoExisteTarea() {
        // Given
        given(tareaService.cambiarEstado(any(UUID.class), anyInt()))
                .willReturn(Optional.of(tarea));

        // When
        ResponseEntity<Tarea> response = tareasApiDelegate.actualizarEstadoTarea(tareaId, cambioEstadoTarea);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
        then(response.getBody().getId()).isEqualTo(tareaId);
        verify(tareaService).cambiarEstado(eq(tareaId), eq(2));
    }

    @Test
    void actualizarEstadoTarea_DebeRetornarNotFound_CuandoNoExisteTarea() {
        // Given
        given(tareaService.cambiarEstado(any(UUID.class), anyInt()))
                .willReturn(Optional.empty());

        // When
        ResponseEntity<Tarea> response = tareasApiDelegate.actualizarEstadoTarea(tareaId, cambioEstadoTarea);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        then(response.getBody()).isNull();
        verify(tareaService).cambiarEstado(eq(tareaId), eq(2));
    }
} 