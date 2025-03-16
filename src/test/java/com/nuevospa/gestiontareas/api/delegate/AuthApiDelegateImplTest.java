package com.nuevospa.gestiontareas.api.delegate;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.nuevospa.gestiontareas.dto.LoginRequest;
import com.nuevospa.gestiontareas.dto.LoginResponse;
import com.nuevospa.gestiontareas.model.Rol;
import com.nuevospa.gestiontareas.model.Usuario;
import com.nuevospa.gestiontareas.repository.UsuarioRepository;
import com.nuevospa.gestiontareas.security.JwtTokenUtil;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
class AuthApiDelegateImplTest {

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AuthApiDelegateImpl authApiDelegate;

    private LoginRequest loginRequest;
    private Usuario usuario;
    private UUID usuarioId;

    @BeforeEach
    void setUp() {
        usuarioId = UUID.randomUUID();
        
        loginRequest = new LoginRequest();
        loginRequest.setEmail("usuario@test.com");
        loginRequest.setPassword("password");
        
        usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setNombre("Usuario Test");
        usuario.setApellido("Apellido Test");
        usuario.setEmail("usuario@test.com");
        usuario.setPassword("password");
        usuario.setRol(Rol.USUARIO);
        usuario.setFechaCreacion(LocalDateTime.now());
    }

    @Test
    void login_DebeRetornarToken_CuandoCredencialesValidas(CapturedOutput capturedOutput) {
        // Given
        given(usuarioRepository.findByEmail(any(String.class)))
                .willReturn(Optional.of(usuario));
        given(jwtTokenUtil.generateToken(any(UserDetails.class)))
                .willReturn("jwt-token");

        // When
        ResponseEntity<LoginResponse> response = authApiDelegate.login(loginRequest);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
        then(response.getBody().getToken()).isEqualTo("jwt-token");
        then(response.getBody().getTokenType()).isEqualTo("Bearer");
        then(response.getBody().getUsuarioId()).isEqualTo(usuarioId);
        then(response.getBody().getNombre()).isEqualTo("Usuario Test");
        then(response.getBody().getEmail()).isEqualTo("usuario@test.com");
        then(response.getBody().getRol()).isEqualTo(Rol.USUARIO);
        then(capturedOutput).contains("Procesando solicitud de login para: usuario@test.com");
        then(capturedOutput).contains("Token JWT generado para: usuario@test.com");
        verify(usuarioRepository).findByEmail("usuario@test.com");
        verify(jwtTokenUtil).generateToken(any(UserDetails.class));
    }

    @Test
    void login_DebeLanzarExcepcion_CuandoUsuarioNoExiste() {
        // Given
        given(usuarioRepository.findByEmail(any(String.class)))
                .willReturn(Optional.empty());

        // When/Then
        thenThrownBy(() -> authApiDelegate.login(loginRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Usuario no encontrado");
        verify(usuarioRepository).findByEmail("usuario@test.com");
    }
} 