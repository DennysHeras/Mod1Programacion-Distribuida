package com.distribuida.service;

import com.distribuida.dao.FacturaDetalleRepository;
import com.distribuida.model.FacturaDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaDetalleServiceImpl implements FacturaDetalleService {

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @Override
    public List<FacturaDetalle> findAll() {
        return facturaDetalleRepository.findAll();
    }

    @Override
    public Optional<FacturaDetalle> findById(Integer id) {
        return facturaDetalleRepository.findById(id);
    }

    @Override
    public FacturaDetalle save(FacturaDetalle facturaDetalle) {
        return facturaDetalleRepository.save(facturaDetalle);
    }

    @Override
    public FacturaDetalle update(FacturaDetalle facturaDetalle) {
        if (facturaDetalle.getIdFacturaDetalle() == 0) return null;
        Optional<FacturaDetalle> opt = facturaDetalleRepository.findById(facturaDetalle.getIdFacturaDetalle());
        if (opt.isEmpty()) return null;
        FacturaDetalle actual = opt.get();
        actual.setCantidad(facturaDetalle.getCantidad());
        actual.setSubtotal(facturaDetalle.getSubtotal());
        actual.setFactura(facturaDetalle.getFactura());
        actual.setLibro(facturaDetalle.getLibro());
        return facturaDetalleRepository.save(actual);
    }

    @Override
    public void deleteById(Integer id) {
        facturaDetalleRepository.deleteById(id);
    }
}
