package com.nuevospa.gestiontareas.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.nuevospa.gestiontareas.dto.TareaActualizarDTO;
import com.nuevospa.gestiontareas.dto.TareaCrearDTO;
import com.nuevospa.gestiontareas.dto.TareaDTO;
import com.nuevospa.gestiontareas.model.EstadoTarea;
import com.nuevospa.gestiontareas.model.Prioridad;
import com.nuevospa.gestiontareas.model.Tarea;
import com.nuevospa.gestiontareas.model.Usuario;
import com.nuevospa.gestiontareas.repository.EstadoTareaRepository;
import com.nuevospa.gestiontareas.repository.PrioridadRepository;
import com.nuevospa.gestiontareas.repository.TareaRepository;
import com.nuevospa.gestiontareas.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class TareaServiceTest {

    @Mock
    private TareaRepository tareaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EstadoTareaRepository estadoTareaRepository;

    @Mock
    private PrioridadRepository prioridadRepository;

    @InjectMocks
    private TareaService tareaService;

    private UUID tareaId;
    private UUID usuarioId;
    private Tarea tarea;
    private Usuario usuario;
    private EstadoTarea estadoTarea;
    private Prioridad prioridad;
    private TareaCrearDTO tareaCrearDTO;
    private TareaActualizarDTO tareaActualizarDTO;

    @BeforeEach
    void setUp() {
        tareaId = UUID.randomUUID();
        usuarioId = UUID.randomUUID();

        usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setNombre("Usuario Test");
        usuario.setEmail("usuario@test.com");

        estadoTarea = new EstadoTarea();
        estadoTarea.setId(1);
        estadoTarea.setNombre("PENDIENTE");

        prioridad = new Prioridad();
        prioridad.setId(1);
        prioridad.setNombre("ALTA");

        tarea = new Tarea();
        tarea.setId(tareaId);
        tarea.setTitulo("Tarea de prueba");
        tarea.setDescripcion("Descripción de prueba");
        tarea.setFechaCreacion(LocalDateTime.now());
        tarea.setUsuarioAsignado(usuario);
        tarea.setEstado(estadoTarea);
        tarea.setPrioridad(prioridad);
        tarea.setEstadoId(estadoTarea.getId());
        tarea.setPrioridadId(prioridad.getId());

        tareaCrearDTO = new TareaCrearDTO();
        tareaCrearDTO.setTitulo("Nueva tarea");
        tareaCrearDTO.setDescripcion("Nueva descripción");
        tareaCrearDTO.setEstadoId(1);
        tareaCrearDTO.setPrioridadId(1);
        tareaCrearDTO.setUsuarioId(usuarioId);

        tareaActualizarDTO = new TareaActualizarDTO();
        tareaActualizarDTO.setId(tareaId);
        tareaActualizarDTO.setTitulo("Tarea actualizada");
        tareaActualizarDTO.setDescripcion("Descripción actualizada");
        tareaActualizarDTO.setEstadoId(1);
        tareaActualizarDTO.setPrioridadId(1);
        tareaActualizarDTO.setUsuarioId(usuarioId);
    }

    @Test
    void findFiltered_DebeRetornarPageTarea_CuandoNoHayFiltros() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Tarea> expectedPage = new PageImpl<>(Arrays.asList(tarea), pageable, 1);
        when(tareaRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);

        // When
        Page<Tarea> result = tareaService.findFiltered(null, null, null, pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getId()).isEqualTo(tareaId);
        verify(tareaRepository).findAll(pageable);
    }

    @Test
    void findFiltered_DebeRetornarPageTarea_CuandoHayFiltroEstado() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Tarea> expectedPage = new PageImpl<>(Arrays.asList(tarea), pageable, 1);
        when(tareaRepository.findByEstadoId(anyInt(), any(Pageable.class))).thenReturn(expectedPage);

        // When
        Page<Tarea> result = tareaService.findFiltered(1, null, null, pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getId()).isEqualTo(tareaId);
        verify(tareaRepository).findByEstadoId(1, pageable);
    }

    @Test
    void findFiltered_DebeRetornarPageTarea_CuandoHayFiltroUsuario() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Tarea> expectedPage = new PageImpl<>(Arrays.asList(tarea), pageable, 1);
        when(tareaRepository.findByUsuarioAsignadoId(any(UUID.class), any(Pageable.class))).thenReturn(expectedPage);

        // When
        Page<Tarea> result = tareaService.findFiltered(null, usuarioId, null, pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getId()).isEqualTo(tareaId);
        verify(tareaRepository).findByUsuarioAsignadoId(usuarioId, pageable);
    }

    @Test
    void findFiltered_DebeRetornarPageTarea_CuandoHayFiltroPrioridad() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Tarea> expectedPage = new PageImpl<>(Arrays.asList(tarea), pageable, 1);
        when(tareaRepository.findByPrioridadId(anyInt(), any(Pageable.class))).thenReturn(expectedPage);

        // When
        Page<Tarea> result = tareaService.findFiltered(null, null, 1, pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getId()).isEqualTo(tareaId);
        verify(tareaRepository).findByPrioridadId(1, pageable);
    }

    @Test
    void findById_DebeRetornarTarea_CuandoExiste() {
        // Given
        when(tareaRepository.findById(any(UUID.class))).thenReturn(Optional.of(tarea));

        // When
        Optional<Tarea> result = tareaService.findById(tareaId);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(tareaId);
        verify(tareaRepository).findById(tareaId);
    }

    @Test
    void save_DebeRetornarTareaGuardada() {
        // Given
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

        // When
        Tarea result = tareaService.save(tarea);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(tareaId);
        verify(tareaRepository).save(tarea);
    }

    @Test
    void deleteById_DebeEliminarTarea() {
        // When
        tareaService.deleteById(tareaId);

        // Then
        verify(tareaRepository).deleteById(tareaId);
    }

    @Test
    void cambiarEstado_DebeRetornarTareaActualizada_CuandoExisteTareaYEstado() {
        // Given
        when(tareaRepository.findById(any(UUID.class))).thenReturn(Optional.of(tarea));
        when(estadoTareaRepository.findById(anyInt())).thenReturn(Optional.of(estadoTarea));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

        // When
        Optional<Tarea> result = tareaService.cambiarEstado(tareaId, 1);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(tareaId);
        assertThat(result.get().getEstado().getId()).isEqualTo(1);
        verify(tareaRepository).findById(tareaId);
        verify(estadoTareaRepository).findById(1);
        verify(tareaRepository).save(tarea);
    }

    @Test
    void cambiarEstado_DebeRetornarEmpty_CuandoNoExisteTarea() {
        // Given
        when(tareaRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // When
        Optional<Tarea> result = tareaService.cambiarEstado(tareaId, 1);

        // Then
        assertThat(result).isEmpty();
        verify(tareaRepository).findById(tareaId);
    }

    @Test
    void cambiarEstado_DebeRetornarEmpty_CuandoNoExisteEstado() {
        // Given
        when(tareaRepository.findById(any(UUID.class))).thenReturn(Optional.of(tarea));
        when(estadoTareaRepository.findById(anyInt())).thenReturn(Optional.empty());

        // When
        Optional<Tarea> result = tareaService.cambiarEstado(tareaId, 1);

        // Then
        assertThat(result).isEmpty();
        verify(tareaRepository).findById(tareaId);
        verify(estadoTareaRepository).findById(1);
    }

    @Test
    void create_DebeRetornarTareaDTO_CuandoDatosValidos() {
        // Given
        when(estadoTareaRepository.findById(anyInt())).thenReturn(Optional.of(estadoTarea));
        when(prioridadRepository.findById(anyInt())).thenReturn(Optional.of(prioridad));
        when(usuarioRepository.findById(any(UUID.class))).thenReturn(Optional.of(usuario));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

        // When
        TareaDTO result = tareaService.create(tareaCrearDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(tareaId);
        verify(estadoTareaRepository).findById(tareaCrearDTO.getEstadoId());
        verify(prioridadRepository).findById(tareaCrearDTO.getPrioridadId());
        verify(usuarioRepository).findById(tareaCrearDTO.getUsuarioId());
        verify(tareaRepository).save(any(Tarea.class));
    }

    @Test
    void create_DebeLanzarExcepcion_CuandoNoExisteEstado() {
        // Given
        when(estadoTareaRepository.findById(anyInt())).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> tareaService.create(tareaCrearDTO))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Estado no encontrado");
    }

    @Test
    void create_DebeLanzarExcepcion_CuandoNoExistePrioridad() {
        // Given
        when(estadoTareaRepository.findById(anyInt())).thenReturn(Optional.of(estadoTarea));
        when(prioridadRepository.findById(anyInt())).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> tareaService.create(tareaCrearDTO))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Prioridad no encontrada");
    }

    @Test
    void create_DebeLanzarExcepcion_CuandoNoExisteUsuario() {
        // Given
        when(estadoTareaRepository.findById(anyInt())).thenReturn(Optional.of(estadoTarea));
        when(prioridadRepository.findById(anyInt())).thenReturn(Optional.of(prioridad));
        when(usuarioRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> tareaService.create(tareaCrearDTO))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Usuario no encontrado");
    }

    @Test
    void update_DebeRetornarTareaDTO_CuandoDatosValidos() {
        // Given
        when(tareaRepository.findById(any(UUID.class))).thenReturn(Optional.of(tarea));
        when(estadoTareaRepository.findById(anyInt())).thenReturn(Optional.of(estadoTarea));
        when(prioridadRepository.findById(anyInt())).thenReturn(Optional.of(prioridad));
        when(usuarioRepository.findById(any(UUID.class))).thenReturn(Optional.of(usuario));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

        // When
        TareaDTO result = tareaService.update(tareaActualizarDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(tareaId);
        verify(tareaRepository).findById(tareaActualizarDTO.getId());
        verify(estadoTareaRepository).findById(tareaActualizarDTO.getEstadoId());
        verify(prioridadRepository).findById(tareaActualizarDTO.getPrioridadId());
        verify(usuarioRepository).findById(tareaActualizarDTO.getUsuarioId());
        verify(tareaRepository).save(any(Tarea.class));
    }

    @Test
    void update_DebeLanzarExcepcion_CuandoNoExisteTarea() {
        // Given
        when(tareaRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> tareaService.update(tareaActualizarDTO))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Tarea no encontrada");
    }
} 