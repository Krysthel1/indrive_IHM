package com.indriveapp.indrive.service;

import com.indriveapp.indrive.model.Oferta;

public interface OfertaService {

    Oferta guardar(Oferta oferta);

    Oferta buscarPorId(Integer id);
}
