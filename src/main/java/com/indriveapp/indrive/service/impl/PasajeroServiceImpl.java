package com.indriveapp.indrive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indriveapp.indrive.model.Pasajero;
import com.indriveapp.indrive.repository.PasajeroRepository;
import com.indriveapp.indrive.service.PasajeroService;

@Service
public class PasajeroServiceImpl
        implements PasajeroService {

    @Autowired
    private PasajeroRepository repository;

    @Override
    public Pasajero guardar(
            Pasajero pasajero) {
        return repository.save(pasajero);
    }
}