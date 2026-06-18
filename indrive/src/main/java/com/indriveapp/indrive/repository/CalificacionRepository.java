package com.indriveapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.model.Calificacion;

public interface CalificacionRepository
        extends JpaRepository<Calificacion, Integer> {
}