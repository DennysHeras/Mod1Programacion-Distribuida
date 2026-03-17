package com.distribuida.service;

import com.distribuida.dao.CarritoRepository;
import com.distribuida.dao.FacturaDetalleRepository;
import com.distribuida.dao.FacturaRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.util.CheckoutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private static final double IVA = 0.15;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Override
    @Transactional
    public Factura checkoutByToken(String token) {

        // 1. Buscar el carrito por token
        Carrito carrito = carritoRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado para token: " + token));

        // 2. Validar que el carrito no esté vacío
        if (carrito.getItems() == null || carrito.getItems().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío. Agregue productos antes de pagar.");
        }

        // 3. Validar stock de cada ítem
        for (CarritoItem item : carrito.getItems()) {
            int stockDisponible = item.getLibro().getNumEjemplares();
            if (stockDisponible < item.getCantidad()) {
                throw new IllegalStateException(
                        "Stock insuficiente para: " + item.getLibro().getTitulo() +
                        ". Disponible: " + stockDisponible +
                        ", Solicitado: " + item.getCantidad()
                );
            }
        }

        // 4. Descontar stock de cada libro
        for (CarritoItem item : carrito.getItems()) {
            item.getLibro().setNumEjemplares(
                    item.getLibro().getNumEjemplares() - item.getCantidad()
            );
            libroRepository.save(item.getLibro());
        }

        // 5. Generar número de factura único basado en la fecha/hora
        String numeroFactura = "F" + DateTimeFormatter
                .ofPattern("yyyyMMddHHmmss")
                .format(LocalDateTime.now());

        // 6. Construir la factura usando el mapper
        Factura factura = CheckoutMapper.construirFacturaDesdeCarrito(carrito, numeroFactura, IVA);

        // 7. Persistir la factura
        Factura facturaGuardada = facturaRepository.save(factura);

        // 8. Construir y persistir cada detalle de factura
        for (CarritoItem item : carrito.getItems()) {
            FacturaDetalle detalle = CheckoutMapper.construirDetalle(facturaGuardada, item);
            facturaDetalleRepository.save(detalle);
        }

        // 9. Limpiar el carrito tras el pago
        carrito.getItems().clear();
        carritoRepository.save(carrito);

        // 10. Retornar la factura generada
        return facturaGuardada;
    }
}
