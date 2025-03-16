package com.nuevospa.gestiontareas.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.nuevospa.gestiontareas.api.delegate.AuthApiDelegateImpl;
import com.nuevospa.gestiontareas.api.delegate.EstadosApiDelegateImpl;
import com.nuevospa.gestiontareas.api.delegate.PrioridadesApiDelegateImpl;
import com.nuevospa.gestiontareas.api.delegate.TareasApiDelegateImpl;
import com.nuevospa.gestiontareas.api.delegate.UsuariosApiDelegateImpl;
import com.nuevospa.gestiontareas.api.generated.ApiApi;
import com.nuevospa.gestiontareas.dto.generated.CambioEstadoTarea;
import com.nuevospa.gestiontareas.dto.generated.EstadoTarea;
import com.nuevospa.gestiontareas.dto.generated.LoginRequest;
import com.nuevospa.gestiontareas.dto.generated.LoginResponse;
import com.nuevospa.gestiontareas.dto.generated.PageTarea;
import com.nuevospa.gestiontareas.dto.generated.Prioridad;
import com.nuevospa.gestiontareas.dto.generated.Tarea;
import com.nuevospa.gestiontareas.dto.generated.Usuario;
import com.nuevospa.gestiontareas.mapper.DtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador principal que implementa la especificación OpenAPI.
 * 
 * Este controlador sigue el enfoque API First, donde la especificación OpenAPI
 * define el contrato de la API antes de la implementación. Implementa la interfaz
 * generada automáticamente a partir de la especificación OpenAPI y delega la
 * lógica de negocio a los delegados correspondientes.
 * 
 * Se recomienda utilizar este controlador en lugar de los controladores tradicionales
 * que están marcados como @Deprecated.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController implements ApiApi {

    private final AuthApiDelegateImpl authApiDelegate;
    private final TareasApiDelegateImpl tareasApiDelegate;
    private final UsuariosApiDelegateImpl usuariosApiDelegate;
    private final EstadosApiDelegateImpl estadosApiDelegate;
    private final PrioridadesApiDelegateImpl prioridadesApiDelegate;
    private final DtoMapper dtoMapper;

    @Override
    public ResponseEntity<Tarea> _actualizarEstadoTarea(UUID id, CambioEstadoTarea cambioEstadoTareaDto) {
        log.info("Actualizando estado de tarea con ID: {}", id);
        return dtoMapper.toTareaResponse(
                tareasApiDelegate.actualizarEstadoTarea(id, dtoMapper.toModel(cambioEstadoTareaDto))
        );
    }

    @Override
    public ResponseEntity<Tarea> _actualizarTarea(UUID id, Tarea tareaDto) {
        log.info("Actualizando tarea con ID: {}", id);
        return dtoMapper.toTareaResponse(
                tareasApiDelegate.actualizarTarea(id, dtoMapper.toModel(tareaDto))
        );
    }

    @Override
    public ResponseEntity<Void> _crearTarea(Tarea tareaDto) {
        log.info("Creando nueva tarea");
        return tareasApiDelegate.crearTarea(dtoMapper.toModel(tareaDto));
    }

    @Override
    public ResponseEntity<Void> _eliminarTarea(UUID id) {
        log.info("Eliminando tarea con ID: {}", id);
        return tareasApiDelegate.eliminarTarea(id);
    }

    @Override
    public ResponseEntity<LoginResponse> _login(LoginRequest loginRequestDto) {
        log.info("Procesando solicitud de login para: {}", loginRequestDto.getEmail());
        return dtoMapper.toLoginResponse(
                authApiDelegate.login(dtoMapper.toModel(loginRequestDto))
        );
    }

    @Override
    public ResponseEntity<List<EstadoTarea>> _obtenerEstados() {
        log.info("Obteniendo todos los estados de tareas");
        return dtoMapper.toEstadoListResponse(
                estadosApiDelegate.obtenerEstados()
        );
    }

    @Override
    public ResponseEntity<List<Prioridad>> _obtenerPrioridades() {
        log.info("Obteniendo todas las prioridades");
        return dtoMapper.toPrioridadListResponse(
                prioridadesApiDelegate.obtenerPrioridades()
        );
    }

    @Override
    public ResponseEntity<Tarea> _obtenerTareaPorId(UUID id) {
        log.info("Obteniendo tarea con ID: {}", id);
        return dtoMapper.toTareaResponse(
                tareasApiDelegate.obtenerTareaPorId(id)
        );
    }

    @Override
    public ResponseEntity<PageTarea> _obtenerTareas(Integer page, Integer size, Integer estadoId, UUID usuarioId, Integer prioridad, String sort) {
        log.info("Obteniendo tareas paginadas. Página: {}, Tamaño: {}", page, size);
        return dtoMapper.toPageTareaResponse(
                tareasApiDelegate.obtenerTareas(page, size, estadoId, usuarioId, prioridad, sort)
        );
    }

    @Override
    public ResponseEntity<Usuario> _obtenerUsuarioPorId(UUID id) {
        log.info("Obteniendo usuario con ID: {}", id);
        return dtoMapper.toUsuarioResponse(
                usuariosApiDelegate.obtenerUsuarioPorId(id)
        );
    }

    @Override
    public ResponseEntity<List<Usuario>> _obtenerUsuarios() {
        log.info("Obteniendo todos los usuarios");
        return dtoMapper.toUsuarioListResponse(
                usuariosApiDelegate.obtenerUsuarios()
        );
    }
} 