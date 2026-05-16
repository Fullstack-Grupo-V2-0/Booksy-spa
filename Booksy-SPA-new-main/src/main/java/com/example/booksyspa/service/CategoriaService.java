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
        return categoriaRepository.findById(id).orElse(null);
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
        // Si la categoría no existe, el repositorio lanzará una excepción.
        // Si una categoría está en uso por un libro, la base de datos debería impedir el borrado
        // debido a la restricción de clave foránea.
        categoriaRepository.deleteById(id);
    }
}