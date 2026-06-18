package com.indriveapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.model.Conductor;

public interface ConductorRepository
        extends JpaRepository<Conductor, Integer> {
}