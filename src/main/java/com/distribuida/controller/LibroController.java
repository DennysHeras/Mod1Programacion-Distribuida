package com.distribuida.controller;

import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Libro>> findAll() {
        return ResponseEntity.ok(libroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> findOne(@PathVariable int id) {
        Optional<Libro> libro = libroService.findById(id);
        if (libro.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(libro.get());
    }

    @PostMapping
    public ResponseEntity<Libro> create(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.save(libro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> update(@PathVariable int id, @RequestBody Libro libro) {
        libro.setIdLibro(id);
        Libro actualizado = libroService.update(libro);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (libroService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        libroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
