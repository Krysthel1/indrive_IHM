package com.indriveapp.indrive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indriveapp.indrive.model.Conductor;
import com.indriveapp.indrive.repository.ConductorRepository;
import com.indriveapp.indrive.service.ConductorService;

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