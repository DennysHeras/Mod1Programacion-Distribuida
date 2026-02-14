package com.distribuida.controller;

import com.distribuida.model.Factura;
import com.distribuida.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Factura>> findAll() {
        return ResponseEntity.ok(facturaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> findOne(@PathVariable int id) {
        Optional<Factura> factura = facturaService.findById(id);
        if (factura.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(factura.get());
    }

    @PostMapping
    public ResponseEntity<Factura> create(@RequestBody Factura factura) {
        return ResponseEntity.ok(facturaService.save(factura));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> update(@PathVariable int id, @RequestBody Factura factura) {
        factura.setIdFactura(id);
        Factura actualizado = facturaService.update(factura);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (facturaService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        facturaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
