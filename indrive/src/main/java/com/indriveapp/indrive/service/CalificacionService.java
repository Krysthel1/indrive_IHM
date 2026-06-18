package com.indriveapp.service;

import com.indriveapp.model.Calificacion;

public interface CalificacionService {

    Calificacion guardar(Calificacion calificacion);

    Calificacion buscarPorId(Integer id);
}
