package com.indriveapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.indriveapp.model.*;
import com.indriveapp.service.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasajeroService pasajeroService;

    @Autowired
    private ConductorService conductorService;

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registro")
    public String registro() {
        return "auth/registro";
    }

    @PostMapping("/login")
    public String iniciarSesion(
            @RequestParam String correo,
            @RequestParam String password,
            Model model) {

        Usuario usuario =
                usuarioService.login(correo, password);

        if (usuario == null) {
            model.addAttribute(
                    "error",
                    "Correo o contraseña incorrectos");
            return "auth/login";
        }

        if (usuario.getRol().equals("PASAJERO")) {
            return "redirect:/pasajero/dashboard";
        }

        return "redirect:/conductor/dashboard";
    }

    @PostMapping("/registro")
    public String registrar(
            @ModelAttribute Usuario usuario,
            @RequestParam(required = false)
            String licencia,
            @RequestParam(required = false)
            String marca,
            @RequestParam(required = false)
            String modelo,
            @RequestParam(required = false)
            String placa,
            @RequestParam(required = false)
            String color) {

        Usuario nuevoUsuario =
                usuarioService.guardar(usuario);

        if (usuario.getRol().equals("PASAJERO")) {

            Pasajero pasajero = new Pasajero();
            pasajero.setUsuario(nuevoUsuario);

            pasajeroService.guardar(pasajero);
        }

        if (usuario.getRol().equals("CONDUCTOR")) {

            Conductor conductor =
                    new Conductor();

            conductor.setUsuario(nuevoUsuario);
            conductor.setLicencia(licencia);
            conductor.setDisponibilidad(true);

            conductor =
                    conductorService.guardar(conductor);

            Vehiculo vehiculo =
                    new Vehiculo();

            vehiculo.setConductor(conductor);
            vehiculo.setMarca(marca);
            vehiculo.setModelo(modelo);
            vehiculo.setPlaca(placa);
            vehiculo.setColor(color);

            vehiculoService.guardar(vehiculo);
        }

        return "redirect:/auth/login";
    }
}