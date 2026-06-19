package com.indriveapp.indrive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.indriveapp.indrive.model.Viaje;
import com.indriveapp.indrive.service.ViajeService;

@Controller
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    @GetMapping("/crear")
    public String crearViaje(Model model) {
        model.addAttribute("viaje", new Viaje());
        return "pasajero/seleccionar-destino";
    }

    @PostMapping("/guardar")
    public String guardarViaje(@ModelAttribute Viaje viaje) {
        viaje.setEstado("PENDIENTE");
        viajeService.guardar(viaje);
        return "redirect:/pasajero/conductores";
    }

    @GetMapping("/aceptar/{viajeId}/{conductorId}")
    public String aceptarViaje(
            @PathVariable Integer viajeId,
            @PathVariable Integer conductorId) {
        
        Viaje viaje = viajeService.buscarPorId(viajeId);
        if (viaje != null) {
            viaje.setEstado("ACEPTADO");
            viajeService.guardar(viaje);
        }
        
        return "redirect:/conductor/viaje";
    }

    @GetMapping("/completar/{viajeId}")
    public String completarViaje(@PathVariable Integer viajeId) {
        Viaje viaje = viajeService.buscarPorId(viajeId);
        if (viaje != null) {
            viaje.setEstado("COMPLETADO");
            viajeService.guardar(viaje);
        }
        return "redirect:/conductor/historial";
    }
}