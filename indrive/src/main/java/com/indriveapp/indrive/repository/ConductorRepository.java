package com.indriveapp.indrive.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.indrive.model.Conductor;

public interface ConductorRepository
        extends JpaRepository<Conductor, Integer> {
    Optional<Conductor> findByUsuarioIdUsuario(Integer idUsuario);
}