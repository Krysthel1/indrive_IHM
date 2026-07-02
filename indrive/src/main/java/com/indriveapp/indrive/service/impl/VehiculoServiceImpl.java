package com.indriveapp.indrive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indriveapp.indrive.model.Vehiculo;
import com.indriveapp.indrive.repository.VehiculoRepository;
import com.indriveapp.indrive.service.VehiculoService;

@Service
public class VehiculoServiceImpl
        implements VehiculoService {

    @Autowired
    private VehiculoRepository repository;

    @Override
    public Vehiculo guardar(
            Vehiculo vehiculo) {
        return repository.save(vehiculo);
    }
}