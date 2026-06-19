package com.indriveapp.indrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.indrive.model.Viaje;

public interface ViajeRepository
        extends JpaRepository<Viaje, Integer> {
}