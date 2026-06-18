package com.indriveapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.model.Vehiculo;

public interface VehiculoRepository
        extends JpaRepository<Vehiculo, Integer> {
}