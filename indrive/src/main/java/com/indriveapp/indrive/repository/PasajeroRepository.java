package com.indriveapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.model.Pasajero;

public interface PasajeroRepository
        extends JpaRepository<Pasajero, Integer> {
}
