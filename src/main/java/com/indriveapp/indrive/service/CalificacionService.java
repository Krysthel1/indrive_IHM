package com.indriveapp.indrive.service;

import java.util.List;
import com.indriveapp.indrive.model.Calificacion;

public interface CalificacionService {

    Calificacion guardar(Calificacion calificacion);

    Calificacion buscarPorId(Integer id);

    List<Calificacion> buscarPorConductor(Integer idConductor);
}
