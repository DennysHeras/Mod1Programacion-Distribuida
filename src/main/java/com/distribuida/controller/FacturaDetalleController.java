package com.distribuida.controller;

import com.distribuida.model.FacturaDetalle;
import com.distribuida.service.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/factura-detalles")
public class FacturaDetalleController {

    @Autowired
    private FacturaDetalleService facturaDetalleService;

    @GetMapping("/findAll")
    public ResponseEntity<List<FacturaDetalle>> findAll() {
        return ResponseEntity.ok(facturaDetalleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDetalle> findOne(@PathVariable int id) {
        Optional<FacturaDetalle> detalle = facturaDetalleService.findById(id);
        if (detalle.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(detalle.get());
    }

    @PostMapping
    public ResponseEntity<FacturaDetalle> create(@RequestBody FacturaDetalle facturaDetalle) {
        return ResponseEntity.ok(facturaDetalleService.save(facturaDetalle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaDetalle> update(@PathVariable int id, @RequestBody FacturaDetalle facturaDetalle) {
        facturaDetalle.setIdFacturaDetalle(id);
        FacturaDetalle actualizado = facturaDetalleService.update(facturaDetalle);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (facturaDetalleService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        facturaDetalleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
