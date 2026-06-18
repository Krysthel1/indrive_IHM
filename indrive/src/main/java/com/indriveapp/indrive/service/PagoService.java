package com.indriveapp.service;

import com.indriveapp.model.Pago;

public interface PagoService {

    Pago guardar(Pago pago);

    Pago buscarPorId(Integer id);
}
