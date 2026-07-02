package com.indriveapp.indrive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indriveapp.indrive.model.Viaje;
import com.indriveapp.indrive.repository.ViajeRepository;
import com.indriveapp.indrive.service.ViajeService;

@Service
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    @Override
    public Viaje guardar(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

    @Override
    public Viaje buscarPorId(Integer id) {
        return viajeRepository.findById(id).orElse(null);
    }
}
