package com.nuevospa.gestiontareas.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.nuevospa.gestiontareas.model.Usuario;

public interface UsuariosApiDelegate {
    ResponseEntity<List<Usuario>> obtenerUsuarios();
    ResponseEntity<Usuario> obtenerUsuarioPorId(UUID id);
} 