package com.nuevospa.gestiontareas.api.delegate;

import com.nuevospa.gestiontareas.api.UsuariosApiDelegate;
import com.nuevospa.gestiontareas.model.Usuario;
import com.nuevospa.gestiontareas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UsuariosApiDelegateImpl implements UsuariosApiDelegate {

    private final UsuarioService usuarioService;

    @Override
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        log.info("Obteniendo lista de usuarios");
        List<Usuario> usuarios = usuarioService.findAll();
        // Por seguridad, no devolver las contraseñas
        usuarios.forEach(u -> u.setPassword(null));
        return ResponseEntity.ok(usuarios);
    }

    @Override
    public ResponseEntity<Usuario> obtenerUsuarioPorId(UUID id) {
        log.info("Obteniendo usuario con ID: {}", id);
        
        return usuarioService.findById(id)
                .map(usuario -> {
                    // Por seguridad, no devolver la contraseña
                    usuario.setPassword(null);
                    return ResponseEntity.ok(usuario);
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 