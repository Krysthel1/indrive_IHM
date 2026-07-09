package com.indriveapp.indrive.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indriveapp.indrive.model.Pago;

public interface PagoRepository
        extends JpaRepository<Pago, Integer> {

    @Query("SELECT p FROM Pago p WHERE p.viaje.pasajero.idPasajero = :idPasajero ORDER BY p.viaje.fecha DESC")
    List<Pago> findByPasajeroOrderByFechaDesc(@Param("idPasajero") Integer idPasajero);

    @Query("SELECT p FROM Pago p WHERE p.viaje.pasajero.idPasajero = :idPasajero AND MONTH(p.viaje.fecha) = :mes AND YEAR(p.viaje.fecha) = :anio ORDER BY p.viaje.fecha DESC")
    List<Pago> findByPasajeroAndMes(@Param("idPasajero") Integer idPasajero, @Param("mes") int mes, @Param("anio") int anio);
}