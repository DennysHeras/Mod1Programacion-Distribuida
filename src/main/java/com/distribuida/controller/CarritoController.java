package com.distribuida.controller;

import com.distribuida.dto.AddItemRequest;
import com.distribuida.dto.UpdateItemRequest;
import com.distribuida.model.Carrito;
import com.distribuida.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // ─── Endpoints por token ──────────────────────────────────────────────────

    /** Obtiene o crea el carrito para un token dado */
    @GetMapping("/token/{token}")
    public ResponseEntity<Carrito> getByToken(@PathVariable String token) {
        Carrito carrito = carritoService.getOrCreateByToken(token);
        return ResponseEntity.ok(carrito);
    }

    /** Agrega un libro al carrito */
    @PostMapping("/token/{token}/items")
    public ResponseEntity<Carrito> addItem(@PathVariable String token,
                                           @RequestBody AddItemRequest request) {
        Carrito carrito = carritoService.addItem(token, request.getIdLibro(), request.getCantidad());
        return ResponseEntity.ok(carrito);
    }

    /** Actualiza la cantidad de un libro en el carrito */
    @PutMapping("/token/{token}/items/{idLibro}")
    public ResponseEntity<Carrito> updateItem(@PathVariable String token,
                                              @PathVariable int idLibro,
                                              @RequestBody UpdateItemRequest request) {
        Carrito carrito = carritoService.updateItemCantidad(token, idLibro, request.getCantidad());
        return ResponseEntity.ok(carrito);
    }

    /** Elimina un libro del carrito */
    @DeleteMapping("/token/{token}/items/{idLibro}")
    public ResponseEntity<Carrito> removeItem(@PathVariable String token,
                                              @PathVariable int idLibro) {
        Carrito carrito = carritoService.removeItem(token, idLibro);
        return ResponseEntity.ok(carrito);
    }

    /** Vacía el carrito completo */
    @DeleteMapping("/token/{token}")
    public ResponseEntity<Carrito> clearCarrito(@PathVariable String token) {
        Carrito carrito = carritoService.clearByToken(token);
        return ResponseEntity.ok(carrito);
    }
}
