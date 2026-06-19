package com.indriveapp.indrive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF para permitir peticiones POST sin tokens
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permite el acceso libre a todas las rutas (HTML, JS, CSS, controladores)
            )
            .formLogin(form -> form.disable()) // Deshabilita el login automático de Spring Security
            .httpBasic(basic -> basic.disable()); // Deshabilita la autenticación básica
        return http.build();
    }
}
