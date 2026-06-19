package com.indriveapp.indrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.indriveapp.indrive.model.Calificacion;

public interface CalificacionRepository
        extends JpaRepository<Calificacion, Integer> {
    List<Calificacion> findByViajeConductorIdConductor(Integer idConductor);
}