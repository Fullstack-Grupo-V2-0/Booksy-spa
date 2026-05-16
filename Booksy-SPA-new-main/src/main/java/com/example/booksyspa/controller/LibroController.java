package com.example.booksyspa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booksyspa.model.Libro;
import com.example.booksyspa.service.LibroService;

@RestController
@RequestMapping("/api/v2/libros")
@CrossOrigin("*")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros() {
        return ResponseEntity.ok(libroService.getLibros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable int id) {
        Libro libro = libroService.getLibroId(id);
        return libro != null ? ResponseEntity.ok(libro) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createLibro(@RequestBody Libro libro) {
        try {
            Libro nuevoLibro = libroService.saveLibro(libro);
            return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLibro(@PathVariable int id, @RequestBody Libro libro) {
        try {
            Libro libroActualizado = libroService.updateLibro(id, libro);
            return ResponseEntity.ok(libroActualizado);
        } catch (RuntimeException e) {
            // Puede ser un 404 (no encontrado) o 400 (datos inválidos como ISBN duplicado)
            if (e.getMessage().contains("no existe")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable int id) {
        try {
            libroService.deleteLibro(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoints de búsqueda

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Libro> getLibroByIsbn(@PathVariable String isbn) {
        Libro libro = libroService.getLibroPorIsbn(isbn);
        return libro != null ? ResponseEntity.ok(libro) : ResponseEntity.notFound().build();
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Libro>> getLibrosByCategoria(@PathVariable int idCategoria) {
        List<Libro> libros = libroService.getLibrosPorCategoria(idCategoria);
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/autor/{idAutor}")
    public ResponseEntity<List<Libro>> getLibrosByAutor(@PathVariable int idAutor) {
        List<Libro> libros = libroService.getLibrosPorAutor(idAutor);
        return ResponseEntity.ok(libros);
    }
}