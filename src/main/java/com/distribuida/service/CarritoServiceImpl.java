package com.distribuida.service;

import com.distribuida.dao.CarritoItemRepository;
import com.distribuida.dao.CarritoRepository;
import com.distribuida.dao.ClienteRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import com.distribuida.model.Cliente;
import com.distribuida.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CarritoServiceImpl implements CarritoService {

    private static final BigDecimal IVA = BigDecimal.valueOf(0.15);

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoItemRepository carritoItemRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LibroRepository libroRepository;

    // ─── Por ID de cliente ────────────────────────────────────────────────────

    @Override
    @Transactional
    public Carrito getOrCreateByClienteId(int idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + idCliente));
        return carritoRepository.findByCliente(cliente)
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setCliente(cliente);
                    nuevo.setSubtotal(BigDecimal.ZERO);
                    nuevo.setDescuento(BigDecimal.ZERO);
                    nuevo.setImpuestos(BigDecimal.ZERO);
                    nuevo.setTotal(BigDecimal.ZERO);
                    return carritoRepository.save(nuevo);
                });
    }

    @Override
    @Transactional
    public Carrito addItem(int idCliente, int idLibro, int cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        Carrito carrito = getOrCreateByClienteId(idCliente);
        return agregarItemAlCarrito(carrito, idLibro, cantidad);
    }

    @Override
    @Transactional
    public Carrito updateItemCantidad(int idCliente, int idLibro, int cantidad) {
        Carrito carrito = getOrCreateByClienteId(idCliente);
        return actualizarCantidad(carrito, idLibro, cantidad);
    }

    @Override
    @Transactional
    public Carrito removeItem(int idCliente, int idLibro) {
        Carrito carrito = getOrCreateByClienteId(idCliente);
        return eliminarItem(carrito, idLibro);
    }

    @Override
    @Transactional
    public Carrito clear(int idCliente) {
        Carrito carrito = getOrCreateByClienteId(idCliente);
        return limpiarCarrito(carrito);
    }

    @Override
    @Transactional(readOnly = true)
    public Carrito getByIdCliente(int idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + idCliente));
        return carritoRepository.findByCliente(cliente)
                .orElseThrow(() -> new IllegalArgumentException("El cliente no tiene carrito activo"));
    }

    // ─── Por token ────────────────────────────────────────────────────────────

    @Override
    @Transactional
    public Carrito getOrCreateByToken(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("El token no puede ser nulo o vacío");
        }
        return carritoRepository.findByToken(token)
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setToken(token);
                    nuevo.setSubtotal(BigDecimal.ZERO);
                    nuevo.setDescuento(BigDecimal.ZERO);
                    nuevo.setImpuestos(BigDecimal.ZERO);
                    nuevo.setTotal(BigDecimal.ZERO);
                    return carritoRepository.save(nuevo);
                });
    }

    @Override
    @Transactional
    public Carrito addItem(String token, int idLibro, int cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        Carrito carrito = getOrCreateByToken(token);
        return agregarItemAlCarrito(carrito, idLibro, cantidad);
    }

    @Override
    @Transactional
    public Carrito updateItemCantidad(String token, int idLibro, int cantidad) {
        Carrito carrito = getOrCreateByToken(token);
        return actualizarCantidad(carrito, idLibro, cantidad);
    }

    @Override
    @Transactional
    public Carrito removeItem(String token, int idLibro) {
        Carrito carrito = getOrCreateByToken(token);
        return eliminarItem(carrito, idLibro);
    }

    @Override
    @Transactional
    public Carrito clearByToken(String token) {
        Carrito carrito = getOrCreateByToken(token);
        return limpiarCarrito(carrito);
    }

    @Override
    @Transactional(readOnly = true)
    public Carrito getByToken(String token) {
        return carritoRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado para token: " + token));
    }

    // ─── Métodos internos reutilizables ───────────────────────────────────────

    private Carrito agregarItemAlCarrito(Carrito carrito, int idLibro, int cantidad) {
        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + idLibro));

        Optional<CarritoItem> itemExistente = carritoItemRepository
                .findByCarritoAndLibro(carrito, libro);

        if (itemExistente.isPresent()) {
            CarritoItem item = itemExistente.get();
            item.setCantidad(item.getCantidad() + cantidad);
            item.calcularTotal();
            carritoItemRepository.save(item);
        } else {
            CarritoItem nuevoItem = new CarritoItem();
            nuevoItem.setCarrito(carrito);
            nuevoItem.setLibro(libro);
            nuevoItem.setCantidad(cantidad);
            nuevoItem.setPrecioUnitario(BigDecimal.valueOf(libro.getPrecio()));
            nuevoItem.calcularTotal();
            carrito.getItems().add(nuevoItem);
        }

        carrito.recomputarTotales(IVA);
        return carritoRepository.save(carrito);
    }

    private Carrito actualizarCantidad(Carrito carrito, int idLibro, int cantidad) {
        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + idLibro));

        CarritoItem item = carritoItemRepository.findByCarritoAndLibro(carrito, libro)
                .orElseThrow(() -> new IllegalArgumentException("El libro no está en el carrito"));

        if (item.getCarrito().getIdCarrito() != carrito.getIdCarrito()) {
            throw new IllegalArgumentException("El ítem no pertenece a este carrito");
        }

        if (cantidad == 0) {
            carrito.getItems().remove(item);
            carritoItemRepository.delete(item);
        } else {
            item.setCantidad(cantidad);
            item.calcularTotal();
            carritoItemRepository.save(item);
        }

        carrito.recomputarTotales(IVA);
        return carritoRepository.save(carrito);
    }

    private Carrito eliminarItem(Carrito carrito, int idLibro) {
        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + idLibro));

        CarritoItem item = carritoItemRepository.findByCarritoAndLibro(carrito, libro)
                .orElseThrow(() -> new IllegalArgumentException("El libro no está en el carrito"));

        carrito.getItems().remove(item);
        carritoItemRepository.delete(item);
        carrito.recomputarTotales(IVA);
        return carritoRepository.save(carrito);
    }

    private Carrito limpiarCarrito(Carrito carrito) {
        carrito.getItems().clear();
        carrito.setSubtotal(BigDecimal.ZERO);
        carrito.setDescuento(BigDecimal.ZERO);
        carrito.setImpuestos(BigDecimal.ZERO);
        carrito.setTotal(BigDecimal.ZERO);
        return carritoRepository.save(carrito);
    }
}
