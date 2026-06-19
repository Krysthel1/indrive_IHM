package com.indriveapp.indrive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import java.time.LocalDate;

import com.indriveapp.indrive.model.*;
import com.indriveapp.indrive.repository.*;

@Controller
@RequestMapping("/conductor")
public class ConductorController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    private Usuario getUsuarioLogueado(HttpSession session) {
        return (Usuario) session.getAttribute("usuarioLogueado");
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null || !"CONDUCTOR".equals(usuario.getRol())) {
            return "redirect:/auth/login";
        }

        Conductor conductor = conductorRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (conductor == null) {
            return "redirect:/auth/login";
        }

        // Calcular ganancias de hoy
        List<Viaje> viajes = viajeRepository.findByConductorIdConductorOrderByIdViajeDesc(conductor.getIdConductor());
        double gananciasHoy = viajes.stream()
                .filter(v -> "COMPLETADO".equals(v.getEstado()))
                .filter(v -> v.getFecha() != null && v.getFecha().toLocalDate().equals(LocalDate.now()))
                .mapToDouble(v -> v.getPrecioPropuesto() != null ? v.getPrecioPropuesto() : 0.0)
                .sum();

        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("conductor", conductor);
        model.addAttribute("gananciasHoy", String.format("%.2f", gananciasHoy));
        return "conductor/dashboard";
    }

    @GetMapping("/solicitudes")
    public String solicitudes(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        Conductor conductor = conductorRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (conductor == null) return "redirect:/auth/login";

        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("conductor", conductor);
        return "conductor/solicitudes";
    }

    @GetMapping("/oferta")
    public String oferta(@RequestParam Integer viajeId, HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        Conductor conductor = conductorRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (conductor == null) return "redirect:/auth/login";

        Viaje viaje = viajeRepository.findById(viajeId).orElse(null);
        if (viaje == null) {
            return "redirect:/conductor/solicitudes";
        }

        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("viaje", viaje);
        model.addAttribute("conductorId", conductor.getIdConductor());
        return "conductor/enviar-oferta";
    }

    @GetMapping("/viaje")
    public String viaje(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        Conductor conductor = conductorRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (conductor == null) return "redirect:/auth/login";

        // Buscar viaje activo en curso o aceptado
        Optional<Viaje> viajeOpt = viajeRepository.findTopByConductorIdConductorAndEstadoInOrderByIdViajeDesc(
                conductor.getIdConductor(), Arrays.asList("ACEPTADO", "EN_CURSO"));

        if (viajeOpt.isEmpty()) {
            return "redirect:/conductor/dashboard";
        }

        Viaje viaje = viajeOpt.get();
        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("viaje", viaje);
        model.addAttribute("pasajeroUsuario", viaje.getPasajero().getUsuario());
        return "conductor/viaje-actual";
    }

    @GetMapping("/historial")
    public String historial(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        Conductor conductor = conductorRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (conductor == null) return "redirect:/auth/login";

        List<Viaje> viajes = viajeRepository.findByConductorIdConductorOrderByIdViajeDesc(conductor.getIdConductor());
        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("viajes", viajes);
        return "conductor/historial";
    }

    @GetMapping("/ganancias")
    public String ganancias(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        Conductor conductor = conductorRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (conductor == null) return "redirect:/auth/login";

        List<Viaje> viajes = viajeRepository.findByConductorIdConductorOrderByIdViajeDesc(conductor.getIdConductor());
        double gananciasTotales = viajes.stream()
                .filter(v -> "COMPLETADO".equals(v.getEstado()))
                .mapToDouble(v -> v.getPrecioPropuesto() != null ? v.getPrecioPropuesto() : 0.0)
                .sum();

        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("gananciasTotales", String.format("%.2f", gananciasTotales));
        model.addAttribute("viajes", viajes);
        return "conductor/ganancias";
    }

    @Autowired
    private CalificacionRepository calificacionRepository;

    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        Conductor conductor = conductorRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (conductor == null) return "redirect:/auth/login";

        // Obtener vehículo asociado
        Vehiculo vehiculo = vehiculoRepository.findAll().stream()
                .filter(v -> v.getConductor() != null && v.getConductor().getIdConductor().equals(conductor.getIdConductor()))
                .findFirst().orElse(null);

        // Obtener calificaciones
        List<Calificacion> calificaciones = calificacionRepository.findByViajeConductorIdConductor(conductor.getIdConductor());
        
        double promedioCalificaciones = 0.0;
        if (!calificaciones.isEmpty()) {
            promedioCalificaciones = calificaciones.stream()
                    .mapToInt(c -> c.getPuntuacion() != null ? c.getPuntuacion() : 0)
                    .average()
                    .orElse(0.0);
        }

        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("conductor", conductor);
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("calificaciones", calificaciones);
        model.addAttribute("promedioCalificaciones", String.format(Locale.US, "%.1f", promedioCalificaciones));
        model.addAttribute("totalCalificaciones", calificaciones.size());
        return "conductor/perfil";
    }

    // --- REST APIs para interacción en tiempo real ---

    @PostMapping("/api/toggle-disponibilidad")
    @ResponseBody
    public Map<String, Object> toggleDisponibilidad(HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) {
            res.put("success", false);
            return res;
        }
        Conductor conductor = conductorRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (conductor != null) {
            conductor.setDisponibilidad(!conductor.isDisponibilidad());
            conductorRepository.save(conductor);
            res.put("success", true);
            res.put("disponibilidad", conductor.isDisponibilidad());
        } else {
            res.put("success", false);
        }
        return res;
    }

    @GetMapping("/api/solicitudes")
    @ResponseBody
    public List<Map<String, Object>> obtenerSolicitudes(HttpSession session) {
        Usuario usuario = getUsuarioLogueado(session);
        if (usuario == null) return Collections.emptyList();

        Conductor conductor = conductorRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (conductor == null || !conductor.isDisponibilidad()) {
            return Collections.emptyList();
        }

        List<Viaje> viajesPendientes = viajeRepository.findByEstado("PENDIENTE");
        List<Map<String, Object>> response = new ArrayList<>();
        for (Viaje v : viajesPendientes) {
            Map<String, Object> map = new HashMap<>();
            map.put("idViaje", v.getIdViaje());
            map.put("origen", v.getOrigen());
            map.put("destino", v.getDestino());
            map.put("precioPropuesto", v.getPrecioPropuesto());
            map.put("nombrePasajero", v.getPasajero().getUsuario().getNombres() + " " + v.getPasajero().getUsuario().getApellidos());
            response.add(map);
        }
        return response;
    }
}