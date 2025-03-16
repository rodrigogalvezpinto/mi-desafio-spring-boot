package com.nuevospa.gestiontareas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuevospa.gestiontareas.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    
    Optional<Usuario> findByEmail(String email);
    
    boolean existsByEmail(String email);
} 