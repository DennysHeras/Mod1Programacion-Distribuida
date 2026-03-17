package com.distribuida.controller;

import com.distribuida.model.Factura;
import com.distribuida.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin(origins = "*")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    /**
     * Procesa el pago del carrito identificado por token.
     * Valida stock, genera factura y vacía el carrito.
     */
    @PostMapping("/token/{token}")
    public ResponseEntity<Factura> checkoutByToken(@PathVariable String token) {
        try {
            Factura factura = checkoutService.checkoutByToken(token);
            return ResponseEntity.ok(factura);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
