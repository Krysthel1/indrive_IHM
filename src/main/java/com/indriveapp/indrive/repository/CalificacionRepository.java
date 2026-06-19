package com.indriveapp.indrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.indrive.model.Calificacion;

public interface CalificacionRepository
        extends JpaRepository<Calificacion, Integer> {
}