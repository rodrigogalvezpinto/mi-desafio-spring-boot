package com.nuevospa.gestiontareas.api;

import com.nuevospa.gestiontareas.api.delegate.*;
import com.nuevospa.gestiontareas.dto.CambioEstadoTarea;
import com.nuevospa.gestiontareas.dto.LoginRequest;
import com.nuevospa.gestiontareas.dto.LoginResponse;
import com.nuevospa.gestiontareas.dto.PageTarea;
import com.nuevospa.gestiontareas.mapper.DtoMapper;
import com.nuevospa.gestiontareas.model.EstadoTarea;
import com.nuevospa.gestiontareas.model.Prioridad;
import com.nuevospa.gestiontareas.model.Rol;
import com.nuevospa.gestiontareas.model.Tarea;
import com.nuevospa.gestiontareas.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApiControllerTest {

    @Mock
    private AuthApiDelegateImpl authApiDelegate;

    @Mock
    private TareasApiDelegateImpl tareasApiDelegate;

    @Mock
    private UsuariosApiDelegateImpl usuariosApiDelegate;

    @Mock
    private EstadosApiDelegateImpl estadosApiDelegate;

    @Mock
    private PrioridadesApiDelegateImpl prioridadesApiDelegate;

    @Spy
    private DtoMapper dtoMapper;

    @InjectMocks
    private ApiController apiController;

    private UUID tareaId;
    private UUID usuarioId;
    private Tarea tarea;
    private Usuario usuario;
    private EstadoTarea estadoTarea;
    private Prioridad prioridad;
    private LoginRequest loginRequest;
    private LoginResponse loginResponse;
    private CambioEstadoTarea cambioEstadoTarea;

    @BeforeEach
    void setUp() {
        tareaId = UUID.randomUUID();
        usuarioId = UUID.randomUUID();

        // Inicializar objetos de prueba
        tarea = new Tarea();
        tarea.setId(tareaId);
        tarea.setTitulo("Tarea de prueba");
        tarea.setDescripcion("Descripción de prueba");
        tarea.setEstadoId(1);
        tarea.setPrioridadId(2);
        tarea.setUsuarioAsignadoId(usuarioId);
        tarea.setFechaCreacion(LocalDateTime.now());
        tarea.setFechaVencimiento(LocalDateTime.now().plusDays(7));

        usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setNombre("Usuario");
        usuario.setApellido("Prueba");
        usuario.setEmail("usuario@test.com");
        usuario.setRol(Rol.USUARIO);
        usuario.setFechaCreacion(LocalDateTime.now());

        estadoTarea = new EstadoTarea();
        estadoTarea.setId(1);
        estadoTarea.setNombre("PENDIENTE");
        estadoTarea.setDescripcion("Tarea pendiente");

        prioridad = new Prioridad();
        prioridad.setId(2);
        prioridad.setNombre("MEDIA");
        prioridad.setDescripcion("Prioridad media");

        loginRequest = new LoginRequest();
        loginRequest.setEmail("usuario@test.com");
        loginRequest.setPassword("password123");

        loginResponse = new LoginResponse();
        loginResponse.setToken("jwt-token");
        loginResponse.setTokenType("Bearer");
        loginResponse.setUsuarioId(usuarioId);
        loginResponse.setNombre("Usuario Prueba");
        loginResponse.setEmail("usuario@test.com");
        loginResponse.setRol(Rol.USUARIO);

        cambioEstadoTarea = new CambioEstadoTarea();
        cambioEstadoTarea.setEstadoId(2);
    }

    @Test
    void testObtenerTareaPorId() {
        // Configurar mock
        when(tareasApiDelegate.obtenerTareaPorId(eq(tareaId))).thenReturn(ResponseEntity.ok(tarea));

        // Ejecutar método a probar
        ResponseEntity<com.nuevospa.gestiontareas.dto.generated.Tarea> response = apiController._obtenerTareaPorId(tareaId);

        // Verificar resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(tareaId.toString(), response.getBody().getId().toString());
        assertEquals("Tarea de prueba", response.getBody().getTitulo());
    }

    @Test
    void testObtenerEstados() {
        // Configurar mock
        List<EstadoTarea> estados = Arrays.asList(estadoTarea);
        when(estadosApiDelegate.obtenerEstados()).thenReturn(ResponseEntity.ok(estados));

        // Ejecutar método a probar
        ResponseEntity<List<com.nuevospa.gestiontareas.dto.generated.EstadoTarea>> response = apiController._obtenerEstados();

        // Verificar resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(1, response.getBody().get(0).getId());
        assertEquals("PENDIENTE", response.getBody().get(0).getNombre());
    }

    @Test
    void testLogin() {
        // Configurar mock
        when(authApiDelegate.login(any(LoginRequest.class))).thenReturn(ResponseEntity.ok(loginResponse));

        // Crear DTO de entrada
        com.nuevospa.gestiontareas.dto.generated.LoginRequest loginRequestDto = new com.nuevospa.gestiontareas.dto.generated.LoginRequest();
        loginRequestDto.setEmail("usuario@test.com");
        loginRequestDto.setPassword("password123");

        // Ejecutar método a probar
        ResponseEntity<com.nuevospa.gestiontareas.dto.generated.LoginResponse> response = apiController._login(loginRequestDto);

        // Verificar resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("jwt-token", response.getBody().getToken());
        assertEquals("Bearer", response.getBody().getTokenType());
        assertEquals(usuarioId.toString(), response.getBody().getUsuarioId().toString());
    }
} 