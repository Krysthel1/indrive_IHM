package com.indriveapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.model.Viaje;

public interface ViajeRepository
        extends JpaRepository<Viaje, Integer> {
}