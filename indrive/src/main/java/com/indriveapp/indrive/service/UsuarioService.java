package com.indriveapp.service;

import java.util.Optional;
import com.indriveapp.model.Usuario;

public interface UsuarioService {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorCorreo(String correo);

    Usuario login(String correo, String password);
}