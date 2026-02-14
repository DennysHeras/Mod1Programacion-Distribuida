package com.distribuida.controller;

import com.distribuida.model.Autor;
import com.distribuida.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Autor>> findAll() {
        return ResponseEntity.ok(autorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> findOne(@PathVariable int id) {
        Optional<Autor> autor = autorService.findById(id);
        if (autor.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(autor.get());
    }

    @PostMapping
    public ResponseEntity<Autor> create(@RequestBody Autor autor) {
        return ResponseEntity.ok(autorService.save(autor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> update(@PathVariable int id, @RequestBody Autor autor) {
        autor.setIdAutor(id);
        Autor actualizado = autorService.update(autor);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (autorService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        autorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
