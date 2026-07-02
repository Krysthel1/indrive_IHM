package com.indriveapp.indrive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.indriveapp.indrive.model.Pago;
import com.indriveapp.indrive.model.Viaje;
import com.indriveapp.indrive.service.PagoService;
import com.indriveapp.indrive.service.ViajeService;

@Controller
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private ViajeService viajeService;

    @GetMapping("/procesar/{viajeId}")
    public String procesarPago(@PathVariable Integer viajeId, Model model) {
        model.addAttribute("viajeId", viajeId);
        return "pasajero/pago";
    }

    @PostMapping("/guardar")
    public String guardarPago(
            @RequestParam Integer viajeId,
            @RequestParam Double monto,
            @RequestParam String metodo) {
        
        Pago pago = new Pago();
        pago.setMonto(monto);
        pago.setMetodo(metodo);
        
        Viaje viaje = viajeService.buscarPorId(viajeId);
        if (viaje != null) {
            pago.setViaje(viaje);
        }
        
        pagoService.guardar(pago);
        
        return "redirect:/pasajero/calificacion";
    }
}
