package com.distribuida.util;

import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;

import java.time.LocalDateTime;

/**
 * Clase utilitaria para construir entidades de facturación a partir del carrito.
 * No tiene estado ni dependencias; todos sus métodos son estáticos.
 */
public final class CheckoutMapper {

    private CheckoutMapper() {
        // No instanciable
    }

    /**
     * Construye una Factura a partir del carrito activo.
     *
     * @param carrito       Carrito con items cargados
     * @param numeroFactura Número de factura generado externamente
     * @param tasaIVA       Tasa de IVA en decimal (ej. 0.15)
     * @return Factura sin persistir (id = 0)
     */
    public static Factura construirFacturaDesdeCarrito(Carrito carrito,
                                                       String numeroFactura,
                                                       double tasaIVA) {
        double subtotal = carrito.getItems().stream()
                .mapToDouble(item -> item.getTotal().doubleValue())
                .sum();

        double iva = subtotal * tasaIVA;
        double total = subtotal + iva;

        Factura factura = new Factura();
        factura.setNumFactura(numeroFactura);
        factura.setFecha(LocalDateTime.now());
        factura.setTotalNeto((float) subtotal);
        factura.setIva((float) iva);
        factura.setTotal((float) total);
        factura.setCliente(carrito.getCliente());

        return factura;
    }

    /**
     * Construye un FacturaDetalle a partir de un ítem del carrito.
     *
     * @param factura     Factura ya persistida (con id asignado)
     * @param carritoItem Ítem del carrito
     * @return FacturaDetalle sin persistir
     */
    public static FacturaDetalle construirDetalle(Factura factura, CarritoItem carritoItem) {
        FacturaDetalle detalle = new FacturaDetalle();
        detalle.setFactura(factura);
        detalle.setLibro(carritoItem.getLibro());
        detalle.setCantidad(carritoItem.getCantidad());
        detalle.setSubtotal(carritoItem.getTotal().floatValue());
        return detalle;
    }
}
