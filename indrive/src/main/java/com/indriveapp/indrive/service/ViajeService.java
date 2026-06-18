package com.indriveapp.service;

import com.indriveapp.model.Viaje;

public interface ViajeService {

    Viaje guardar(Viaje viaje);

    Viaje buscarPorId(Integer id);
}
