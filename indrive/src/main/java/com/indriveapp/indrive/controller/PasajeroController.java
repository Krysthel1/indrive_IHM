package com.indriveapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pasajero")
public class PasajeroController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "pasajero/dashboard";
    }

    @GetMapping("/destino")
    public String destino() {
        return "pasajero/seleccionar-destino";
    }

    @GetMapping("/precio")
    public String precio() {
        return "pasajero/proponer-precio";
    }

    @GetMapping("/conductores")
    public String conductores() {
        return "pasajero/conductores";
    }

    @GetMapping("/seguimiento")
    public String seguimiento() {
        return "pasajero/seguimiento";
    }

    @GetMapping("/pago")
    public String pago() {
        return "pasajero/pago";
    }

    @GetMapping("/calificacion")
    public String calificacion() {
        return "pasajero/calificacion";
    }
}