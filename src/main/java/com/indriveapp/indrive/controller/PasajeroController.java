package com.indriveapp.indrive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.*;

import com.indriveapp.indrive.model.*;
import com.indriveapp.indrive.repository.*;

@Controller
@RequestMapping("/pasajero")
public class PasajeroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    private Usuario getUsuarioLogueado(HttpSession session) {
        return (Usuario) session.getAttribute("usuarioLogueado");
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null || !"PASAJERO".equals(usuario.getRol())) {
            return "redirect:/auth/login";
        }

        Pasajero pasajero = pasajeroRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (pasajero == null) {
            return "redirect:/auth/login";
        }

        // Obtener historial de viajes del pasajero
        List<Viaje> viajes = viajeRepository.findByPasajeroIdPasajeroOrderByIdViajeDesc(pasajero.getIdPasajero());
        long viajesCompletados = viajes.stream().filter(v -> "COMPLETADO".equals(v.getEstado())).count();

        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("viajesCompletados", viajesCompletados);
        return "pasajero/dashboard";
    }

    @GetMapping("/destino")
    public String destino(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("viaje", new Viaje());
        return "pasajero/seleccionar-destino";
    }

    @GetMapping("/precio")
    public String precio(@RequestParam String origen, @RequestParam String destino, HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("origen", origen);
        model.addAttribute("destino", destino);
        return "pasajero/proponer-precio";
    }

    @GetMapping("/conductores")
    public String conductores(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        Pasajero pasajero = pasajeroRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (pasajero == null) return "redirect:/auth/login";

        // Buscar el viaje activo o pendiente del pasajero
        Optional<Viaje> viajeOpt = viajeRepository.findTopByPasajeroIdPasajeroAndEstadoInOrderByIdViajeDesc(
                pasajero.getIdPasajero(), Arrays.asList("PENDIENTE", "ACEPTADO", "EN_CURSO"));

        if (viajeOpt.isEmpty()) {
            return "redirect:/pasajero/dashboard";
        }

        Viaje viaje = viajeOpt.get();
        if ("ACEPTADO".equals(viaje.getEstado()) || "EN_CURSO".equals(viaje.getEstado())) {
            return "redirect:/pasajero/seguimiento";
        }

        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("viaje", viaje);
        return "pasajero/conductores";
    }

    @GetMapping("/seguimiento")
    public String seguimiento(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        Pasajero pasajero = pasajeroRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (pasajero == null) return "redirect:/auth/login";

        Optional<Viaje> viajeOpt = viajeRepository.findTopByPasajeroIdPasajeroAndEstadoInOrderByIdViajeDesc(
                pasajero.getIdPasajero(), Arrays.asList("ACEPTADO", "EN_CURSO"));

        if (viajeOpt.isEmpty()) {
            return "redirect:/pasajero/dashboard";
        }

        Viaje viaje = viajeOpt.get();
        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("viaje", viaje);
        model.addAttribute("conductor", viaje.getConductor());
        return "pasajero/seguimiento";
    }

    @GetMapping("/pago")
    public String pago(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        Pasajero pasajero = pasajeroRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (pasajero == null) return "redirect:/auth/login";

        // Buscamos el último viaje del pasajero (incluso si está en proceso de pago/completado)
        Optional<Viaje> viajeOpt = viajeRepository.findTopByPasajeroIdPasajeroAndEstadoInOrderByIdViajeDesc(
                pasajero.getIdPasajero(), Arrays.asList("ACEPTADO", "EN_CURSO", "COMPLETADO"));

        if (viajeOpt.isEmpty()) {
            return "redirect:/pasajero/dashboard";
        }

        Viaje viaje = viajeOpt.get();
        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("viaje", viaje);
        return "pasajero/pago";
    }

    @GetMapping("/calificacion")
    public String calificacion(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        Pasajero pasajero = pasajeroRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (pasajero != null) {
            Optional<Viaje> viajeOpt = viajeRepository.findTopByPasajeroIdPasajeroAndEstadoInOrderByIdViajeDesc(
                    pasajero.getIdPasajero(), Arrays.asList("COMPLETADO"));
            if (viajeOpt.isPresent()) {
                model.addAttribute("viajeId", viajeOpt.get().getIdViaje());
            } else {
                return "redirect:/pasajero/dashboard";
            }
        } else {
            return "redirect:/auth/login";
        }

        model.addAttribute("usuarioLogueado", usuario);
        return "pasajero/calificacion";
    }

    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("usuarioLogueado", usuario);
        return "pasajero/perfil";
    }

    @GetMapping("/historial")
    public String historial(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        Pasajero pasajero = pasajeroRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (pasajero == null) return "redirect:/auth/login";

        List<Viaje> viajes = viajeRepository.findByPasajeroIdPasajeroOrderByIdViajeDesc(pasajero.getIdPasajero());
        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("viajes", viajes);
        return "pasajero/historial";
    }
}