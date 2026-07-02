package com.indriveapp.indrive.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indriveapp.indrive.model.Usuario;
import com.indriveapp.indrive.repository.UsuarioRepository;
import com.indriveapp.indrive.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public Usuario login(String correo, String password) {

        Optional<Usuario> usuario =
                usuarioRepository.findByCorreo(correo);

        if (usuario.isPresent()
                && usuario.get().getPassword().equals(password)) {
            return usuario.get();
        }

        return null;
    }
}