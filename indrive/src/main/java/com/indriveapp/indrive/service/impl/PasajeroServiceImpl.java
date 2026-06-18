package com.indriveapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indriveapp.model.Pasajero;
import com.indriveapp.repository.PasajeroRepository;
import com.indriveapp.service.PasajeroService;

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