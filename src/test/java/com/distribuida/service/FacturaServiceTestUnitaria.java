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
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceTestUnitaria {

    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaServiceImpl facturaService;

    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    void setup() {
        cliente = new Cliente(1, "1701234567", "Juan", "Perez", "Calle 1", null, null);
        factura = new Factura();
        factura.setIdFactura(1);
        factura.setNumFactura("FAC-001");
        factura.setFecha(LocalDateTime.now());
        factura.setTotalNeto(100.0f);
        factura.setIva(15.0f);
        factura.setTotal(115.0f);
        factura.setCliente(cliente);
    }

    @Test
    void testFindAll() {
        when(facturaRepository.findAll()).thenReturn(List.of(factura));
        List<Factura> result = facturaService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(facturaRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdExistente() {
        when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));
        Optional<Factura> result = facturaService.findById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdFactura());
        verify(facturaRepository, times(1)).findById(1);
    }

    @Test
    void testFindByIdNoExistente() {
        when(facturaRepository.findById(999)).thenReturn(Optional.empty());
        Optional<Factura> result = facturaService.findById(999);
        assertTrue(result.isEmpty());
        verify(facturaRepository, times(1)).findById(999);
    }

    @Test
    void testSave() {
        when(facturaRepository.save(any(Factura.class))).thenReturn(factura);
        Factura result = facturaService.save(factura);
        assertNotNull(result);
        assertEquals(1, result.getIdFactura());
        verify(facturaRepository, times(1)).save(factura);
    }

    @Test
    void testUpdateExistente() {
        when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));
        when(facturaRepository.save(any(Factura.class))).thenReturn(factura);
        Factura result = facturaService.update(factura);
        assertNotNull(result);
        verify(facturaRepository, times(1)).findById(1);
        verify(facturaRepository, times(1)).save(any(Factura.class));
    }

    @Test
    void testUpdateNoExistente() {
        when(facturaRepository.findById(999)).thenReturn(Optional.empty());
        Factura f = new Factura();
        f.setIdFactura(999);
        Factura result = facturaService.update(f);
        assertNull(result);
        verify(facturaRepository, times(1)).findById(999);
        verify(facturaRepository, never()).save(any());
    }

    @Test
    void testDeleteExistente() {
        doNothing().when(facturaRepository).deleteById(1);
        facturaService.deleteById(1);
        verify(facturaRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteNoExistente() {
        doThrow(EmptyResultDataAccessException.class).when(facturaRepository).deleteById(999);
        assertThrows(EmptyResultDataAccessException.class, () -> facturaService.deleteById(999));
        verify(facturaRepository, times(1)).deleteById(999);
    }
}
