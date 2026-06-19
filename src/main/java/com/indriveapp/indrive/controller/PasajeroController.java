package com.indriveapp.indrive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.indriveapp.indrive.model.Viaje;

@Controller
@RequestMapping("/pasajero")
public class PasajeroController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "pasajero/dashboard";
    }

    @GetMapping("/destino")
    public String destino(Model model) {
        model.addAttribute("viaje", new Viaje());
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