package com.indriveapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.model.Oferta;

public interface OfertaRepository
        extends JpaRepository<Oferta, Integer> {
}