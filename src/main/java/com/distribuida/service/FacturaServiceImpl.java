package com.distribuida.service;

import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Optional<Factura> findById(Integer id) {
        return facturaRepository.findById(id);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura update(Factura factura) {
        if (factura.getIdFactura() == 0) return null;
        Optional<Factura> opt = facturaRepository.findById(factura.getIdFactura());
        if (opt.isEmpty()) return null;
        Factura actual = opt.get();
        actual.setNumFactura(factura.getNumFactura());
        actual.setFecha(factura.getFecha());
        actual.setTotalNeto(factura.getTotalNeto() != null ? factura.getTotalNeto().floatValue() : 0f);
        actual.setIva((float) factura.getIva());
        actual.setTotal((float) factura.getTotal());
        actual.setCliente(factura.getCliente());
        return facturaRepository.save(actual);
    }

    @Override
    public void deleteById(Integer id) {
        facturaRepository.deleteById(id);
    }
}
