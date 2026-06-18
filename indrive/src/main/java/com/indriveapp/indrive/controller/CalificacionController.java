package com.indriveapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.indriveapp.model.Calificacion;
import com.indriveapp.service.CalificacionService;

@Controller
@RequestMapping("/calificacion")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

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
        
        calificacionService.guardar(calificacion);
        
        return "redirect:/pasajero/dashboard";
    }
}