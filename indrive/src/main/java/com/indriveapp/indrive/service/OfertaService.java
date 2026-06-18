package com.indriveapp.service;

import com.indriveapp.model.Oferta;

public interface OfertaService {

    Oferta guardar(Oferta oferta);

    Oferta buscarPorId(Integer id);
}
