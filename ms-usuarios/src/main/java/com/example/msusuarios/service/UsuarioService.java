package com.example.msusuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.msusuarios.model.Usuario;
import com.example.msusuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario con id " + id + " no existe"));
    }

    public Usuario getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario con email " + email + " no existe"));
    }

    public Usuario saveUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Ya existe un usuario con el email: " + usuario.getEmail());
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario updateUsuario(int id, Usuario datos) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario con id " + id + " no existe"));

        Optional.ofNullable(datos.getNombre()).ifPresent(existente::setNombre);
        Optional.ofNullable(datos.getEmail()).ifPresent(existente::setEmail);
        Optional.ofNullable(datos.getPassword()).ifPresent(existente::setPassword);
        Optional.ofNullable(datos.getRol()).ifPresent(existente::setRol);

        return usuarioRepository.save(existente);
    }

    @Transactional
    public void deleteUsuario(int id) {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario con id " + id + " no existe"));
        usuarioRepository.deleteById(id);
    }
}
