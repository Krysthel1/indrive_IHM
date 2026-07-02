package com.indriveapp.indrive.service;

import com.indriveapp.indrive.model.Viaje;

public interface ViajeService {

    Viaje guardar(Viaje viaje);

    Viaje buscarPorId(Integer id);
}
