package com.indriveapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indriveapp.model.Conductor;
import com.indriveapp.repository.ConductorRepository;
import com.indriveapp.service.ConductorService;

@Service
public class ConductorServiceImpl
        implements ConductorService {

    @Autowired
    private ConductorRepository repository;

    @Override
    public Conductor guardar(
            Conductor conductor) {
        return repository.save(conductor);
    }
}