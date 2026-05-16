package com.example.booksyspa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booksyspa.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    Optional<Libro> findByIsbn(String isbn);

    // Spring Data JPA puede "navegar" a través de las relaciones.
    // Esto buscará libros donde el ID de la entidad Categoria coincida.
    List<Libro> findByCategoriaIdCategoria(int idCategoria);

    // Busca libros donde el ID de la entidad Autor coincida.
    List<Libro> findByAutorIdAutor(int idAutor);
}