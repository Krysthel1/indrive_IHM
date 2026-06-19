package com.indriveapp.indrive.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.indriveapp.indrive.model.Viaje;

public interface ViajeRepository
        extends JpaRepository<Viaje, Integer> {
    List<Viaje> findByEstado(String estado);
    List<Viaje> findByPasajeroIdPasajeroOrderByIdViajeDesc(Integer idPasajero);
    List<Viaje> findByConductorIdConductorOrderByIdViajeDesc(Integer idConductor);
    Optional<Viaje> findTopByPasajeroIdPasajeroAndEstadoInOrderByIdViajeDesc(Integer idPasajero, List<String> estados);
    Optional<Viaje> findTopByConductorIdConductorAndEstadoInOrderByIdViajeDesc(Integer idConductor, List<String> estados);
}