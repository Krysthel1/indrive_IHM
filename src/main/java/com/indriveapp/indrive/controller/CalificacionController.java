package com.indriveapp.indrive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.indriveapp.indrive.model.Calificacion;
import com.indriveapp.indrive.model.Viaje;
import com.indriveapp.indrive.service.CalificacionService;
import com.indriveapp.indrive.service.ViajeService;

@Controller
@RequestMapping("/calificacion")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @Autowired
    private ViajeService viajeService;

    @GetMapping("/nueva/{viajeId}")
    public String nuevaCalificacion(@PathVariable Integer viajeId, Model model) {
        model.addAttribute("viajeId", viajeId);
        return "calificacion/nueva";
    }

    @PostMapping("/guardar")
    public String guardarCalificacion(
            @RequestParam Integer viajeId,
            @RequestParam Integer puntuacion,
            @RequestParam(required = false) String comentario) {
        
        Calificacion calificacion = new Calificacion();
        calificacion.setPuntuacion(puntuacion);
        calificacion.setComentario(comentario);
        
        Viaje viaje = viajeService.buscarPorId(viajeId);
        if (viaje != null) {
            calificacion.setViaje(viaje);
        }
        
        calificacionService.guardar(calificacion);
        
        return "redirect:/pasajero/dashboard";
    }
}