package com.indriveapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/usuarios")
    public String usuarios() {
        return "admin/usuarios";
    }

    @GetMapping("/viajes")
    public String viajes() {
        return "admin/viajes";
    }
}
