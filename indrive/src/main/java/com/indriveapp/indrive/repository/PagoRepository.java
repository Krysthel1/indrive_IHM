package com.indriveapp.indrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.indrive.model.Pago;

public interface PagoRepository
        extends JpaRepository<Pago, Integer> {
}