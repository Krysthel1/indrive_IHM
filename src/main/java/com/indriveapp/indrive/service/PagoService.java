package com.indriveapp.indrive.service;

import com.indriveapp.indrive.model.Pago;

public interface PagoService {

    Pago guardar(Pago pago);

    Pago buscarPorId(Integer id);
}
