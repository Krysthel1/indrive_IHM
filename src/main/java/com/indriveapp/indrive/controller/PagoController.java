package com.indriveapp.indrive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

import com.indriveapp.indrive.model.Pago;
import com.indriveapp.indrive.model.Usuario;
import com.indriveapp.indrive.model.Pasajero;
import com.indriveapp.indrive.service.PagoService;
import com.indriveapp.indrive.repository.PagoRepository;
import com.indriveapp.indrive.repository.PasajeroRepository;

@Controller
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PasajeroRepository pasajeroRepository;

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
        
        pagoService.guardar(pago);
        
        return "redirect:/pasajero/calificacion";
    }

    @GetMapping("/billetera")
    public String verBilletera(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !"PASAJERO".equals(usuario.getRol())) {
            return "redirect:/auth/login";
        }

        Pasajero pasajero = pasajeroRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).orElse(null);
        if (pasajero == null) {
            return "redirect:/auth/login";
        }

        // Obtener todos los pagos del pasajero
        List<Pago> todosLosPagos = pagoRepository.findByPasajeroOrderByFechaDesc(pasajero.getIdPasajero());
        
        double balanceTotal = todosLosPagos.stream().mapToDouble(Pago::getMonto).sum();

        // Obtener pagos del mes actual
        LocalDate hoy = LocalDate.now();
        List<Pago> pagosMes = pagoRepository.findByPasajeroAndMes(pasajero.getIdPasajero(), hoy.getMonthValue(), hoy.getYear());
        
        double gastoMes = pagosMes.stream().mapToDouble(Pago::getMonto).sum();
        int viajesRealizados = pagosMes.size();

        // Para los últimos gastos, mostramos los últimos 3
        List<Pago> ultimosPagos = todosLosPagos.stream().limit(3).toList();

        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("balanceTotal", balanceTotal);
        model.addAttribute("gastoMes", gastoMes);
        model.addAttribute("viajesRealizados", viajesRealizados);
        model.addAttribute("ultimosPagos", ultimosPagos);

        return "pasajero/billetera";
    }
}
