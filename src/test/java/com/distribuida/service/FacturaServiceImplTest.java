package com.distribuida.service;

import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceImplTest {

    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaServiceImpl facturaService;

    private Factura factura;

    private Cliente cliente;

    @BeforeEach
    public void setup() {
        cliente = new Cliente(1, "1701234567", "Juan", "Taipe", "direccion", null, null);

        factura = new Factura();
        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(LocalDateTime.now());
        factura.setTotalNeto(100.00f);
        factura.setIva(15.00f);
        factura.setTotal(115.00f);
        factura.setCliente(cliente);
    }

    @Test
    public void testFindAll() {
        when(facturaRepository.findAll()).thenReturn(List.of(factura));
        List<Factura> facturas = facturaService.findAll();

        assertNotNull(facturas);
        assertEquals(1, facturas.size());

        verify(facturaRepository, times(1)).findAll();
    }
}
