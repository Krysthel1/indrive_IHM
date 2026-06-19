package com.indriveapp.indrive.service;

import com.indriveapp.indrive.model.Calificacion;

public interface CalificacionService {

    Calificacion guardar(Calificacion calificacion);

    Calificacion buscarPorId(Integer id);
}
