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
        return ResponseEntity.ok(libroService.getLibroId(id));
    }

    @PostMapping
    public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.saveLibro(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable int id, @RequestBody Libro libro) {
        Libro libroActualizado = libroService.updateLibro(id, libro);
        return ResponseEntity.ok(libroActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable int id) {
        libroService.deleteLibro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Libro> getLibroByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(libroService.getLibroPorIsbn(isbn));
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
