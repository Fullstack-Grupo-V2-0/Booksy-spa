package com.example.booksyspa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.booksyspa.model.Autor;
import com.example.booksyspa.model.Categoria;
import com.example.booksyspa.model.Libro;
import com.example.booksyspa.repository.AutorRepository;
import com.example.booksyspa.repository.CategoriaRepository;
import com.example.booksyspa.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    public List<Libro> getLibros() {
        return libroRepository.findAll();
    }

    @Transactional
    public Libro saveLibro(Libro libro) {
        // 1. Validar que el autor exista y obtener la entidad completa
        Autor autor = autorRepository.findById(libro.getAutor().getIdAutor())
                .orElseThrow(() -> new RuntimeException("El autor con id " + libro.getAutor().getIdAutor() + " no existe"));

        // 2. Validar que la categoría exista y obtener la entidad completa
        Categoria categoria = categoriaRepository.findById(libro.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("La categoría con id " + libro.getCategoria().getIdCategoria() + " no existe"));

        // 3. Validar que el ISBN no esté duplicado
        libroRepository.findByIsbn(libro.getIsbn()).ifPresent(l -> {
            throw new RuntimeException("El ISBN " + libro.getIsbn() + " ya está registrado.");
        });

        // 4. Asignar las entidades manejadas al libro antes de guardarlo
        libro.setAutor(autor);
        libro.setCategoria(categoria);

        return libroRepository.save(libro);
    }

    public Libro getLibroId(int id) {
        return libroRepository.findById(id).orElse(null);
    }

    public Libro getLibroPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn).orElse(null);
    }

    public List<Libro> getLibrosPorCategoria(int idCategoria) {
        return libroRepository.findByCategoriaIdCategoria(idCategoria);
    }

    public List<Libro> getLibrosPorAutor(int idAutor) {
        return libroRepository.findByAutorIdAutor(idAutor);
    }

    @Transactional
    public Libro updateLibro(int id, Libro libroActualizado) {
        // 1. Buscar el libro existente
        Libro libroExistente = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El libro con id " + id + " no existe"));

        // 2. Validar que el ISBN no se duplique si se intenta cambiar
        if (libroActualizado.getIsbn() != null && !libroActualizado.getIsbn().equals(libroExistente.getIsbn())) {
            libroRepository.findByIsbn(libroActualizado.getIsbn()).ifPresent(l -> {
                throw new RuntimeException("El ISBN " + libroActualizado.getIsbn() + " ya está en uso por otro libro.");
            });
            libroExistente.setIsbn(libroActualizado.getIsbn());
        }

        // 3. Validar y actualizar autor si cambia
        if (libroActualizado.getAutor() != null && libroActualizado.getAutor().getIdAutor() != 0) {
            Autor nuevoAutor = autorRepository.findById(libroActualizado.getAutor().getIdAutor())
                    .orElseThrow(() -> new RuntimeException("El autor con id " + libroActualizado.getAutor().getIdAutor() + " no existe"));
            libroExistente.setAutor(nuevoAutor);
        }

        // 4. Validar y actualizar categoría si cambia
        if (libroActualizado.getCategoria() != null && libroActualizado.getCategoria().getIdCategoria() != 0) {
            Categoria nuevaCategoria = categoriaRepository.findById(libroActualizado.getCategoria().getIdCategoria())
                    .orElseThrow(() -> new RuntimeException("La categoría con id " + libroActualizado.getCategoria().getIdCategoria() + " no existe"));
            libroExistente.setCategoria(nuevaCategoria);
        }

        // 5. Actualizar otros campos de forma selectiva
        Optional.ofNullable(libroActualizado.getTitulo()).ifPresent(libroExistente::setTitulo);
        Optional.ofNullable(libroActualizado.getDescripcion()).ifPresent(libroExistente::setDescripcion);
        Optional.ofNullable(libroActualizado.getFechaPublicacion()).ifPresent(libroExistente::setFechaPublicacion);
        Optional.ofNullable(libroActualizado.getPrecio()).ifPresent(libroExistente::setPrecio);

        return libroRepository.save(libroExistente);
    }

    public void deleteLibro(int id) {
        libroRepository.deleteById(id);
    }
}