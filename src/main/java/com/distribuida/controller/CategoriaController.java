package com.distribuida.controller;

import com.distribuida.model.Categoria;
import com.distribuida.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findOne(@PathVariable int id) {
        Optional<Categoria> categoria = categoriaService.findById(id);
        if (categoria.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoria.get());
    }

    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.save(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable int id, @RequestBody Categoria categoria) {
        categoria.setIdCategoria(id);
        Categoria actualizado = categoriaService.update(categoria);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (categoriaService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
