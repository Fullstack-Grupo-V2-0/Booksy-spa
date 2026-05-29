package com.example.booksyspa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.booksyspa.model.Categoria;
import com.example.booksyspa.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria getCategoriaId(int id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La categoría con id " + id + " no existe"));
    }

    @Transactional
    public Categoria updateCategoria(int id, Categoria categoriaActualizada) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La categoría con id " + id + " no existe"));

        // Actualización selectiva
        Optional.ofNullable(categoriaActualizada.getNombre()).ifPresent(categoriaExistente::setNombre);
        Optional.ofNullable(categoriaActualizada.getDescripcion()).ifPresent(categoriaExistente::setDescripcion);

        return categoriaRepository.save(categoriaExistente);
    }

    @Transactional
    public void deleteCategoria(int id) {
        categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La categoría con id " + id + " no existe"));
        categoriaRepository.deleteById(id);
    }
}