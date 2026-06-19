package com.indriveapp.indrive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/conductor")
public class ConductorController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "conductor/dashboard";
    }

    @GetMapping("/solicitudes")
    public String solicitudes() {
        return "conductor/solicitudes";
    }

    @GetMapping("/oferta")
    public String oferta() {
        return "conductor/enviar-oferta";
    }

    @GetMapping("/viaje")
    public String viaje() {
        return "conductor/viaje-actual";
    }

    @GetMapping("/historial")
    public String historial() {
        return "conductor/historial";
    }

    @GetMapping("/ganancias")
    public String ganancias() {
        return "conductor/ganancias";
    }
}