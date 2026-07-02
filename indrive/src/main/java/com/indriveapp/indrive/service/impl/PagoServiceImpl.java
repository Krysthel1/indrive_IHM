package com.indriveapp.indrive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indriveapp.indrive.model.Pago;
import com.indriveapp.indrive.repository.PagoRepository;
import com.indriveapp.indrive.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public Pago guardar(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago buscarPorId(Integer id) {
        return pagoRepository.findById(id).orElse(null);
    }
}
