package com.indriveapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.indriveapp.model.Oferta;
import com.indriveapp.service.OfertaService;

@Controller
@RequestMapping("/oferta")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

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
        
        Oferta oferta = new Oferta();
        oferta.setPrecio(precio);
        oferta.setTiempoLlegada(tiempoLlegada);
        
        ofertaService.guardar(oferta);
        
        return "redirect:/conductor/solicitudes";
    }
}
