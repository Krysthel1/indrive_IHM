package com.indriveapp.indrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.indrive.model.Vehiculo;

public interface VehiculoRepository
        extends JpaRepository<Vehiculo, Integer> {
}