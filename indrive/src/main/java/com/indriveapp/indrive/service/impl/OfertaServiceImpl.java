package com.indriveapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indriveapp.model.Oferta;
import com.indriveapp.repository.OfertaRepository;
import com.indriveapp.service.OfertaService;

@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Override
    public Oferta guardar(Oferta oferta) {
        return ofertaRepository.save(oferta);
    }

    @Override
    public Oferta buscarPorId(Integer id) {
        return ofertaRepository.findById(id).orElse(null);
    }
}