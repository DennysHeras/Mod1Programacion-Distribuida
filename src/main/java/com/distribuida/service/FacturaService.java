package com.distribuida.service;

import com.distribuida.model.Factura;

import java.util.List;
import java.util.Optional;

public interface FacturaService {
    List<Factura> findAll();
    Optional<Factura> findById(Integer id);
    Factura save(Factura factura);
    Factura update(Factura factura);
    void deleteById(Integer id);
}



