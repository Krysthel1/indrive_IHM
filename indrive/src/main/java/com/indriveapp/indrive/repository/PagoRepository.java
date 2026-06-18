package com.indriveapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.model.Pago;

public interface PagoRepository
        extends JpaRepository<Pago, Integer> {
}