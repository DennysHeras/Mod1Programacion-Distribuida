package com.distribuida.service;

import com.distribuida.model.Carrito;

public interface CarritoService {

    // ─── Operaciones por ID de cliente ────────────────────────────────────────

    Carrito getOrCreateByClienteId(int idCliente);

    Carrito addItem(int idCliente, int idLibro, int cantidad);

    Carrito updateItemCantidad(int idCliente, int idLibro, int cantidad);

    Carrito removeItem(int idCliente, int idLibro);

    Carrito clear(int idCliente);

    Carrito getByIdCliente(int idCliente);

    // ─── Operaciones por token (para usuarios sin sesión) ─────────────────────

    Carrito getOrCreateByToken(String token);

    Carrito addItem(String token, int idLibro, int cantidad);

    Carrito updateItemCantidad(String token, int idLibro, int cantidad);

    Carrito removeItem(String token, int idLibro);

    Carrito clearByToken(String token);

    Carrito getByToken(String token);
}
