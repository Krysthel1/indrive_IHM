package com.indriveapp.indrive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import com.indriveapp.indrive.model.*;
import com.indriveapp.indrive.service.*;

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
            HttpSession session,
            Model model) {

        Usuario usuario =
                usuarioService.login(correo, password);

        if (usuario == null) {
            model.addAttribute(
                    "error",
                    "Correo o contraseña incorrectos");
            return "auth/login";
        }

        // Guardar usuario en la sesión para persistir el inicio de sesión en el navegador
        session.setAttribute("usuarioLogueado", usuario);

        if (usuario.getRol().equals("PASAJERO")) {
            return "redirect:/pasajero/dashboard";
        }

        return "redirect:/conductor/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidar la sesión actual
        session.invalidate();
        return "redirect:/";
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