package com.indriveapp.indrive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.*;

import com.indriveapp.indrive.model.*;
import com.indriveapp.indrive.repository.*;
import com.indriveapp.indrive.service.ViajeService;

@Controller
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping("/crear")
    public String crearViaje(Model model) {
        model.addAttribute("viaje", new Viaje());
        return "pasajero/seleccionar-destino";
    }

    @PostMapping("/guardar")
    public String guardarViaje(@ModelAttribute Viaje viaje, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/auth/login";
        }

        Pasajero pasajero = pasajeroRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (pasajero == null) {
            return "redirect:/auth/login";
        }

        viaje.setPasajero(pasajero);
        viaje.setEstado("PENDIENTE");
        viaje.setFecha(java.time.LocalDateTime.now());
        viajeService.guardar(viaje);

        return "redirect:/pasajero/conductores";
    }

    @GetMapping("/aceptar/{viajeId}/{conductorId}")
    public String aceptarViaje(
            @PathVariable Integer viajeId,
            @PathVariable Integer conductorId) {

        Viaje viaje = viajeService.buscarPorId(viajeId);
        Conductor conductor = conductorRepository.findById(conductorId).orElse(null);
        if (viaje != null && conductor != null) {
            viaje.setConductor(conductor);
            viaje.setEstado("ACEPTADO");
            viajeRepository.save(viaje);
        }

        return "redirect:/conductor/viaje";
    }

    @GetMapping("/completar/{viajeId}")
    public String completarViaje(@PathVariable Integer viajeId) {
        Viaje viaje = viajeService.buscarPorId(viajeId);
        if (viaje != null) {
            viaje.setEstado("COMPLETADO");
            viajeRepository.save(viaje);
        }
        return "redirect:/conductor/historial";
    }

    // --- REST APIs for dynamic AJAX communication ---

    @GetMapping("/api/ofertas")
    @ResponseBody
    public List<Map<String, Object>> obtenerOfertas(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null)
            return Collections.emptyList();

        Pasajero pasajero = pasajeroRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (pasajero == null)
            return Collections.emptyList();

        Optional<Viaje> viajeOpt = viajeRepository.findTopByPasajeroIdPasajeroAndEstadoInOrderByIdViajeDesc(
                pasajero.getIdPasajero(), Arrays.asList("PENDIENTE"));

        if (viajeOpt.isEmpty()) {
            return Collections.emptyList();
        }

        List<Oferta> ofertas = ofertaRepository.findByViajeIdViaje(viajeOpt.get().getIdViaje());
        List<Map<String, Object>> response = new ArrayList<>();
        for (Oferta o : ofertas) {
            Map<String, Object> map = new HashMap<>();
            map.put("idOferta", o.getIdOferta());
            map.put("precio", o.getPrecio());
            map.put("tiempoLlegada", o.getTiempoLlegada());
            map.put("conductorId", o.getConductor().getIdConductor());
            map.put("nombreConductor",
                    o.getConductor().getUsuario().getNombres() + " " + o.getConductor().getUsuario().getApellidos());

            // Buscar vehículo
            Vehiculo v = vehiculoRepository.findAll().stream()
                    .filter(veh -> veh.getConductor() != null
                            && veh.getConductor().getIdConductor().equals(o.getConductor().getIdConductor()))
                    .findFirst().orElse(null);
            if (v != null) {
                map.put("vehiculo",
                        v.getMarca() + " " + v.getModelo() + " • " + v.getColor() + " (" + v.getPlaca() + ")");
            } else {
                map.put("vehiculo", "Vehículo no registrado");
            }
            response.add(map);
        }
        return response;
    }

    @PostMapping("/api/aceptar-oferta")
    @ResponseBody
    public Map<String, Object> aceptarOferta(@RequestParam Integer viajeId, @RequestParam Integer conductorId,
            @RequestParam Double precio) {
        Map<String, Object> res = new HashMap<>();
        Viaje viaje = viajeRepository.findById(viajeId).orElse(null);
        Conductor conductor = conductorRepository.findById(conductorId).orElse(null);

        if (viaje != null && conductor != null) {
            viaje.setConductor(conductor);
            viaje.setPrecioPropuesto(precio);
            viaje.setEstado("ACEPTADO");
            viajeRepository.save(viaje);
            res.put("success", true);
        } else {
            res.put("success", false);
        }
        return res;
    }

    @GetMapping("/api/estado")
    @ResponseBody
    public Map<String, Object> obtenerEstado(HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            res.put("estado", "NO_LOGGED");
            return res;
        }

        String rol = usuario.getRol();
        if ("PASAJERO".equals(rol)) {
            Pasajero pasajero = pasajeroRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
            if (pasajero != null) {
                Optional<Viaje> viajeOpt = viajeRepository.findTopByPasajeroIdPasajeroAndEstadoInOrderByIdViajeDesc(
                        pasajero.getIdPasajero(), Arrays.asList("PENDIENTE", "ACEPTADO", "EN_CURSO", "COMPLETADO"));
                if (viajeOpt.isPresent()) {
                    res.put("estado", viajeOpt.get().getEstado());
                    res.put("viajeId", viajeOpt.get().getIdViaje());
                    return res;
                }
            }
        } else if ("CONDUCTOR".equals(rol)) {
            Conductor conductor = conductorRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
            if (conductor != null) {
                Optional<Viaje> viajeOpt = viajeRepository.findTopByConductorIdConductorAndEstadoInOrderByIdViajeDesc(
                        conductor.getIdConductor(), Arrays.asList("ACEPTADO", "EN_CURSO", "COMPLETADO"));
                if (viajeOpt.isPresent()) {
                    res.put("estado", viajeOpt.get().getEstado());
                    res.put("viajeId", viajeOpt.get().getIdViaje());
                    return res;
                }
            }
        }

        res.put("estado", "NINGUNO");
        return res;
    }

    @PostMapping("/api/cambiar-estado")
    @ResponseBody
    public Map<String, Object> cambiarEstado(@RequestParam Integer viajeId, @RequestParam String nuevoEstado) {
        Map<String, Object> res = new HashMap<>();
        Viaje viaje = viajeRepository.findById(viajeId).orElse(null);
        if (viaje != null) {
            viaje.setEstado(nuevoEstado);
            viajeRepository.save(viaje);
            res.put("success", true);
        } else {
            res.put("success", false);
        }
        return res;
    }

    @PostMapping("/api/cancelar")
    @ResponseBody
    public Map<String, Object> cancelarViaje(@RequestParam Integer viajeId) {
        Map<String, Object> res = new HashMap<>();
        Viaje viaje = viajeRepository.findById(viajeId).orElse(null);
        if (viaje != null) {
            viaje.setEstado("CANCELADO");
            viajeRepository.save(viaje);
            res.put("success", true);
        } else {
            res.put("success", false);
        }
        return res;
    }
}