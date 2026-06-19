package com.indriveapp.indrive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.indriveapp.indrive.model.*;
import com.indriveapp.indrive.repository.*;
import com.indriveapp.indrive.service.OfertaService;

@Controller
@RequestMapping("/oferta")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @GetMapping("/enviar/{viajeId}")
    public String enviarOferta(@PathVariable Integer viajeId, Model model) {
        model.addAttribute("viajeId", viajeId);
        return "conductor/enviar-oferta";
    }

    @PostMapping("/guardar")
    public String guardarOferta(
            @RequestParam Integer viajeId,
            @RequestParam Integer conductorId,
            @RequestParam Double precio,
            @RequestParam Integer tiempoLlegada) {
        
        Viaje viaje = viajeRepository.findById(viajeId).orElse(null);
        Conductor conductor = conductorRepository.findById(conductorId).orElse(null);

        if (viaje != null && conductor != null) {
            Oferta oferta = new Oferta();
            oferta.setViaje(viaje);
            oferta.setConductor(conductor);
            oferta.setPrecio(precio);
            oferta.setTiempoLlegada(tiempoLlegada);
            ofertaService.guardar(oferta);
        }
        
        return "redirect:/conductor/solicitudes";
    }
}
