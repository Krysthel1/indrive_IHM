package com.indriveapp.indrive.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.indrive.model.Pasajero;

public interface PasajeroRepository
        extends JpaRepository<Pasajero, Integer> {
    Optional<Pasajero> findByUsuarioIdUsuario(Integer idUsuario);
}
