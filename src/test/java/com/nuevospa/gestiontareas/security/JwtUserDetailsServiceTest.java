package com.nuevospa.gestiontareas.security;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nuevospa.gestiontareas.model.Rol;
import com.nuevospa.gestiontareas.model.Usuario;
import com.nuevospa.gestiontareas.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class JwtUserDetailsServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setNombre("Usuario Test");
        usuario.setEmail("usuario@test.com");
        usuario.setPassword("password123");
        usuario.setRol(Rol.USUARIO);
    }

    @Test
    void loadUserByUsername_DebeRetornarUserDetails_CuandoUsuarioExiste() {
        // Given
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuario));

        // When
        UserDetails result = jwtUserDetailsService.loadUserByUsername("usuario@test.com");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("usuario@test.com");
        assertThat(result.getPassword()).isEqualTo("password123");
        assertThat(result.getAuthorities()).hasSize(1);
        assertThat(result.getAuthorities().iterator().next().getAuthority()).isEqualTo("ROLE_USUARIO");
        verify(usuarioRepository).findByEmail("usuario@test.com");
    }

    @Test
    void loadUserByUsername_DebeLanzarExcepcion_CuandoUsuarioNoExiste() {
        // Given
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> jwtUserDetailsService.loadUserByUsername("usuario@test.com"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("Usuario no encontrado");
        verify(usuarioRepository).findByEmail("usuario@test.com");
    }
} 