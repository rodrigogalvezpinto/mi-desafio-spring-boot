package com.nuevospa.gestiontareas.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nuevospa.gestiontareas.model.Usuario;
import com.nuevospa.gestiontareas.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    private UUID usuarioId;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioId = UUID.randomUUID();
        
        usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setNombre("Usuario Test");
        usuario.setEmail("usuario@test.com");
        usuario.setPassword("password123");
    }

    @Test
    void findAll_DebeRetornarListaUsuarios() {
        // Given
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        // When
        List<Usuario> result = usuarioService.findAll();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(usuarioId);
        verify(usuarioRepository).findAll();
    }

    @Test
    void findById_DebeRetornarUsuario_CuandoExiste() {
        // Given
        when(usuarioRepository.findById(any(UUID.class))).thenReturn(Optional.of(usuario));

        // When
        Optional<Usuario> result = usuarioService.findById(usuarioId);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(usuarioId);
        verify(usuarioRepository).findById(usuarioId);
    }

    @Test
    void findByEmail_DebeRetornarUsuario_CuandoExiste() {
        // Given
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuario));

        // When
        Optional<Usuario> result = usuarioService.findByEmail("usuario@test.com");

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("usuario@test.com");
        verify(usuarioRepository).findByEmail("usuario@test.com");
    }

    @Test
    void save_DebeRetornarUsuarioGuardado() {
        // Given
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // When
        Usuario result = usuarioService.save(usuario);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(usuarioId);
        verify(passwordEncoder).encode("password123");
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void deleteById_DebeEliminarUsuario() {
        // When
        usuarioService.deleteById(usuarioId);

        // Then
        verify(usuarioRepository).deleteById(usuarioId);
    }
} 