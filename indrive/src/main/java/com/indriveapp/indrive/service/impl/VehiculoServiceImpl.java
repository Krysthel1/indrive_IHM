package com.indriveapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indriveapp.model.Vehiculo;
import com.indriveapp.repository.VehiculoRepository;
import com.indriveapp.service.VehiculoService;

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