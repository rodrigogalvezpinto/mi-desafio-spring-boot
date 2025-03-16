package com.nuevospa.gestiontareas.security;

import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class JwtTokenUtilTest {

    @Spy
    @InjectMocks
    private JwtTokenUtil jwtTokenUtil;

    private UserDetails userDetails;
    private String testSecret = "testSecretKeyThatIsLongEnoughForHS256Algorithm";
    private long testExpiration = 3600000; // 1 hora

    @BeforeEach
    void setUp() {
        // Configurar propiedades del JwtTokenUtil usando ReflectionTestUtils
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", testSecret);
        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", testExpiration);
        
        // Crear UserDetails para las pruebas
        userDetails = User.builder()
                .username("usuario@test.com")
                .password("password")
                .authorities(Collections.emptyList())
                .build();
    }

    @Test
    void generateToken_DebeGenerarTokenValido_CuandoSeProporcionaUserDetails() {
        // Given
        // Se usa el UserDetails configurado en setUp

        // When
        String token = jwtTokenUtil.generateToken(userDetails);

        // Then
        then(token).isNotNull();
        then(token).isNotEmpty();
        then(jwtTokenUtil.validateToken(token, userDetails)).isTrue();
    }

    @Test
    void extractUsername_DebeRetornarUsername_CuandoTokenEsValido() {
        // Given
        String token = jwtTokenUtil.generateToken(userDetails);

        // When
        String username = jwtTokenUtil.extractUsername(token);

        // Then
        then(username).isEqualTo("usuario@test.com");
    }

    @Test
    void extractExpiration_DebeRetornarFechaExpiracion_CuandoTokenEsValido() {
        // Given
        String token = jwtTokenUtil.generateToken(userDetails);
        Date now = new Date();
        Date expectedExpiration = new Date(now.getTime() + testExpiration);

        // When
        Date expiration = jwtTokenUtil.extractExpiration(token);

        // Then
        // Verificamos que la fecha de expiración esté dentro de un rango razonable
        // (no podemos verificar exactamente debido a la diferencia de tiempo entre la generación y la extracción)
        then(expiration).isAfter(now);
        then(expiration).isBefore(new Date(now.getTime() + testExpiration + 10000)); // Añadimos un margen de 10 segundos
    }

    @Test
    void validateToken_DebeRetornarTrue_CuandoTokenEsValidoYUserDetailsCoincide() {
        // Given
        String token = jwtTokenUtil.generateToken(userDetails);

        // When
        boolean isValid = jwtTokenUtil.validateToken(token, userDetails);

        // Then
        then(isValid).isTrue();
    }

    @Test
    void validateToken_DebeRetornarFalse_CuandoUserDetailsNoCoincidenConToken() {
        // Given
        String token = jwtTokenUtil.generateToken(userDetails);
        UserDetails otherUserDetails = User.builder()
                .username("otro@test.com")
                .password("password")
                .authorities(Collections.emptyList())
                .build();

        // When
        boolean isValid = jwtTokenUtil.validateToken(token, otherUserDetails);

        // Then
        then(isValid).isFalse();
    }
} 