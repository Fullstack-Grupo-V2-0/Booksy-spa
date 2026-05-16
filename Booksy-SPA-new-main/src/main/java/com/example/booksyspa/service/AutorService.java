package com.example.booksyspa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.booksyspa.model.Autor;
import com.example.booksyspa.repository.AutorRepository;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> obtenerAutores() {
        return autorRepository.findAll();
    }

    public Autor buscarAutorPorId(int id) {
        return autorRepository.findById(id).orElse(null);
    }

    public Autor buscarAutorPorNombre(String nombre) {
        return autorRepository.findByNombre(nombre).orElse(null);
    }

    public Autor guardar(Autor autor) {
        return autorRepository.save(autor);
    }

    @Transactional
    public Autor actualizar(int id, Autor autorActualizado) {
        // 1. Buscar el autor existente o lanzar una excepción si no se encuentra.
        Autor autorExistente = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El autor con id " + id + " no existe"));

        // 2. Actualizar los campos del autor existente con los valores del autor actualizado,
        // solo si los nuevos valores no son nulos.
        Optional.ofNullable(autorActualizado.getNombre()).ifPresent(autorExistente::setNombre);
        Optional.ofNullable(autorActualizado.getApellido()).ifPresent(autorExistente::setApellido);
        Optional.ofNullable(autorActualizado.getFechaNacimiento()).ifPresent(autorExistente::setFechaNacimiento);
        Optional.ofNullable(autorActualizado.getNacionalidad()).ifPresent(autorExistente::setNacionalidad);
        Optional.ofNullable(autorActualizado.getPremios()).ifPresent(autorExistente::setPremios);

        // 3. Guardar y devolver el autor actualizado.
        return autorRepository.save(autorExistente);
    }

    @Transactional
    public void eliminar(int id) {
        // Lanza una excepción si el autor no existe, lo cual es manejado por el repositorio.
        autorRepository.deleteById(id);
    }
}
