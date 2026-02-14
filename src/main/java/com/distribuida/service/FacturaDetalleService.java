package com.distribuida.service;

import com.distribuida.model.FacturaDetalle;

import java.util.List;
import java.util.Optional;

public interface FacturaDetalleService {
    List<FacturaDetalle> findAll();
    Optional<FacturaDetalle> findById(Integer id);
    FacturaDetalle save(FacturaDetalle facturaDetalle);
    FacturaDetalle update(FacturaDetalle facturaDetalle);
    void deleteById(Integer id);
}



