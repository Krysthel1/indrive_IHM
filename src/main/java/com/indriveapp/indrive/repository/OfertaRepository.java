package com.indriveapp.indrive.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.indrive.model.Oferta;

public interface OfertaRepository
        extends JpaRepository<Oferta, Integer> {
    List<Oferta> findByViajeIdViaje(Integer idViaje);
}