package com.indriveapp.indrive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indriveapp.indrive.model.Calificacion;
import com.indriveapp.indrive.repository.CalificacionRepository;
import com.indriveapp.indrive.service.CalificacionService;

@Service
public class CalificacionServiceImpl implements CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public Calificacion guardar(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @Override
    public Calificacion buscarPorId(Integer id) {
        return calificacionRepository.findById(id).orElse(null);
    }
}
