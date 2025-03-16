package com.nuevospa.gestiontareas.mapper;

import com.nuevospa.gestiontareas.dto.CambioEstadoTarea;
import com.nuevospa.gestiontareas.dto.LoginRequest;
import com.nuevospa.gestiontareas.dto.LoginResponse;
import com.nuevospa.gestiontareas.dto.PageTarea;
import com.nuevospa.gestiontareas.dto.generated.Usuario.RolEnum;
import com.nuevospa.gestiontareas.model.EstadoTarea;
import com.nuevospa.gestiontareas.model.Prioridad;
import com.nuevospa.gestiontareas.model.Rol;
import com.nuevospa.gestiontareas.model.Tarea;
import com.nuevospa.gestiontareas.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
class DtoMapperTest {

    @InjectMocks
    private DtoMapper dtoMapper;

    private UUID id;
    private Tarea tareaModel;
    private com.nuevospa.gestiontareas.dto.generated.Tarea tareaDto;
    private CambioEstadoTarea cambioEstadoModel;
    private com.nuevospa.gestiontareas.dto.generated.CambioEstadoTarea cambioEstadoDto;
    private LoginResponse loginResponseModel;
    private Usuario usuarioModel;
    private EstadoTarea estadoTareaModel;
    private Prioridad prioridadModel;
    private LocalDateTime fechaCreacion;
    private OffsetDateTime fechaCreacionOffset;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        fechaCreacion = LocalDateTime.now();
        fechaCreacionOffset = fechaCreacion.atOffset(ZoneOffset.UTC);

        // Inicializar modelos
        tareaModel = new Tarea();
        tareaModel.setId(id);
        tareaModel.setTitulo("Tarea de prueba");
        tareaModel.setDescripcion("Descripción de prueba");
        tareaModel.setEstadoId(1);
        tareaModel.setPrioridadId(1);
        tareaModel.setFechaCreacion(fechaCreacion);

        cambioEstadoModel = new CambioEstadoTarea();
        cambioEstadoModel.setEstadoId(2);

        loginResponseModel = new LoginResponse();
        loginResponseModel.setToken("jwt-token");
        loginResponseModel.setTokenType("Bearer");
        loginResponseModel.setUsuarioId(id);
        loginResponseModel.setNombre("Usuario Test");
        loginResponseModel.setEmail("usuario@test.com");
        loginResponseModel.setRol(Rol.USUARIO);

        usuarioModel = new Usuario();
        usuarioModel.setId(id);
        usuarioModel.setNombre("Usuario Test");
        usuarioModel.setApellido("Apellido Test");
        usuarioModel.setEmail("usuario@test.com");
        usuarioModel.setPassword("password");
        usuarioModel.setRol(Rol.USUARIO);
        usuarioModel.setFechaCreacion(fechaCreacion);

        estadoTareaModel = EstadoTarea.builder()
                .id(1)
                .nombre("Pendiente")
                .descripcion("Tarea pendiente")
                .build();

        prioridadModel = Prioridad.builder()
                .id(1)
                .nombre("Alta")
                .descripcion("Prioridad alta")
                .build();

        // Inicializar DTOs
        tareaDto = new com.nuevospa.gestiontareas.dto.generated.Tarea("Tarea de prueba", 1, 1);
        tareaDto.setId(id);
        tareaDto.setDescripcion("Descripción de prueba");
        tareaDto.setFechaCreacion(fechaCreacionOffset);

        cambioEstadoDto = new com.nuevospa.gestiontareas.dto.generated.CambioEstadoTarea(2);
    }

    @Test
    void toModel_DebeConvertirDtoATareaModel_CuandoSeProporcionaDto() {
        // Given
        // Se usa el DTO inicializado en setUp

        // When
        Tarea result = dtoMapper.toModel(tareaDto);

        // Then
        then(result).isNotNull();
        then(result.getId()).isEqualTo(id);
        then(result.getTitulo()).isEqualTo("Tarea de prueba");
        then(result.getDescripcion()).isEqualTo("Descripción de prueba");
        then(result.getEstadoId()).isEqualTo(1);
        then(result.getPrioridadId()).isEqualTo(1);
    }

    @Test
    void toDto_DebeConvertirModelATareaDto_CuandoSeProporcionaModel() {
        // Given
        // Se usa el modelo inicializado en setUp

        // When
        com.nuevospa.gestiontareas.dto.generated.Tarea result = dtoMapper.toDto(tareaModel);

        // Then
        then(result).isNotNull();
        then(result.getId()).isEqualTo(id);
        then(result.getTitulo()).isEqualTo("Tarea de prueba");
        then(result.getDescripcion()).isEqualTo("Descripción de prueba");
        then(result.getEstadoId()).isEqualTo(1);
        then(result.getPrioridadId()).isEqualTo(1);
    }

    @Test
    void toModel_DebeConvertirDtoACambioEstadoModel_CuandoSeProporcionaDto() {
        // Given
        // Se usa el DTO inicializado en setUp

        // When
        CambioEstadoTarea result = dtoMapper.toModel(cambioEstadoDto);

        // Then
        then(result).isNotNull();
        then(result.getEstadoId()).isEqualTo(2);
    }

    @Test
    void toDto_DebeConvertirModelACambioEstadoDto_CuandoSeProporcionaModel() {
        // Given
        // Se usa el modelo inicializado en setUp

        // When
        com.nuevospa.gestiontareas.dto.generated.CambioEstadoTarea result = dtoMapper.toDto(cambioEstadoModel);

        // Then
        then(result).isNotNull();
        then(result.getEstadoId()).isEqualTo(2);
    }

    @Test
    void toDto_DebeConvertirModelALoginResponseDto_CuandoSeProporcionaModel() {
        // Given
        // Se usa el modelo inicializado en setUp

        // When
        com.nuevospa.gestiontareas.dto.generated.LoginResponse result = dtoMapper.toDto(loginResponseModel);

        // Then
        then(result).isNotNull();
        then(result.getToken()).isEqualTo("jwt-token");
        then(result.getTokenType()).isEqualTo("Bearer");
        then(result.getUsuarioId()).isEqualTo(id);
        then(result.getNombre()).isEqualTo("Usuario Test");
        then(result.getEmail()).isEqualTo("usuario@test.com");
        then(result.getRol()).isEqualTo("USUARIO");
    }

    @Test
    void toDto_DebeConvertirModelAEstadoTareaDto_CuandoSeProporcionaModel() {
        // Given
        // Se usa el modelo inicializado en setUp

        // When
        com.nuevospa.gestiontareas.dto.generated.EstadoTarea result = dtoMapper.toDto(estadoTareaModel);

        // Then
        then(result).isNotNull();
        then(result.getId()).isEqualTo(1);
        then(result.getNombre()).isEqualTo("Pendiente");
        then(result.getDescripcion()).isEqualTo("Tarea pendiente");
    }

    @Test
    void toEstadoDtoList_DebeConvertirListaDeModelos_CuandoSeProporcionaLista() {
        // Given
        List<EstadoTarea> estados = Arrays.asList(
                EstadoTarea.builder().id(1).nombre("Pendiente").build(),
                EstadoTarea.builder().id(2).nombre("En Progreso").build()
        );

        // When
        List<com.nuevospa.gestiontareas.dto.generated.EstadoTarea> result = dtoMapper.toEstadoDtoList(estados);

        // Then
        then(result).isNotNull();
        then(result.size()).isEqualTo(2);
        then(result.get(0).getId()).isEqualTo(1);
        then(result.get(0).getNombre()).isEqualTo("Pendiente");
        then(result.get(1).getId()).isEqualTo(2);
        then(result.get(1).getNombre()).isEqualTo("En Progreso");
    }

    @Test
    void toTareaResponse_DebeConvertirResponseEntity_CuandoSeProporcionaResponseEntity() {
        // Given
        ResponseEntity<Tarea> response = ResponseEntity.ok(tareaModel);

        // When
        ResponseEntity<com.nuevospa.gestiontareas.dto.generated.Tarea> result = dtoMapper.toTareaResponse(response);

        // Then
        then(result).isNotNull();
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isNotNull();
        then(result.getBody().getId()).isEqualTo(id);
        then(result.getBody().getTitulo()).isEqualTo("Tarea de prueba");
    }

    @Test
    void toLoginResponse_DebeConvertirResponseEntity_CuandoSeProporcionaResponseEntity() {
        // Given
        ResponseEntity<LoginResponse> response = ResponseEntity.ok(loginResponseModel);

        // When
        ResponseEntity<com.nuevospa.gestiontareas.dto.generated.LoginResponse> result = dtoMapper.toLoginResponse(response);

        // Then
        then(result).isNotNull();
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isNotNull();
        then(result.getBody().getToken()).isEqualTo("jwt-token");
        then(result.getBody().getTokenType()).isEqualTo("Bearer");
        then(result.getBody().getUsuarioId()).isEqualTo(id);
    }
} 