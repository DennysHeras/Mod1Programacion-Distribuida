package com.distribuida.service;

import com.distribuida.dao.FacturaDetalleRepository;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaDetalleServiceTestUnitaria {

    @Mock
    private FacturaDetalleRepository facturaDetalleRepository;

    @InjectMocks
    private FacturaDetalleServiceImpl facturaDetalleService;

    private FacturaDetalle facturaDetalle;

    @BeforeEach
    void setup() {
        facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setCantidad(2);
        facturaDetalle.setSubtotal(30.0f);
    }

    @Test
    void testFindAll() {
        when(facturaDetalleRepository.findAll()).thenReturn(List.of(facturaDetalle));
        List<FacturaDetalle> result = facturaDetalleService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(facturaDetalleRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdExistente() {
        when(facturaDetalleRepository.findById(1)).thenReturn(Optional.of(facturaDetalle));
        Optional<FacturaDetalle> result = facturaDetalleService.findById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdFacturaDetalle());
        verify(facturaDetalleRepository, times(1)).findById(1);
    }

    @Test
    void testFindByIdNoExistente() {
        when(facturaDetalleRepository.findById(999)).thenReturn(Optional.empty());
        Optional<FacturaDetalle> result = facturaDetalleService.findById(999);
        assertTrue(result.isEmpty());
        verify(facturaDetalleRepository, times(1)).findById(999);
    }

    @Test
    void testSave() {
        when(facturaDetalleRepository.save(any(FacturaDetalle.class))).thenReturn(facturaDetalle);
        FacturaDetalle result = facturaDetalleService.save(facturaDetalle);
        assertNotNull(result);
        assertEquals(1, result.getIdFacturaDetalle());
        verify(facturaDetalleRepository, times(1)).save(facturaDetalle);
    }

    @Test
    void testUpdateExistente() {
        when(facturaDetalleRepository.findById(1)).thenReturn(Optional.of(facturaDetalle));
        when(facturaDetalleRepository.save(any(FacturaDetalle.class))).thenReturn(facturaDetalle);
        FacturaDetalle result = facturaDetalleService.update(facturaDetalle);
        assertNotNull(result);
        verify(facturaDetalleRepository, times(1)).findById(1);
        verify(facturaDetalleRepository, times(1)).save(any(FacturaDetalle.class));
    }

    @Test
    void testUpdateNoExistente() {
        when(facturaDetalleRepository.findById(999)).thenReturn(Optional.empty());
        FacturaDetalle fd = new FacturaDetalle();
        fd.setIdFacturaDetalle(999);
        FacturaDetalle result = facturaDetalleService.update(fd);
        assertNull(result);
        verify(facturaDetalleRepository, times(1)).findById(999);
        verify(facturaDetalleRepository, never()).save(any());
    }

    @Test
    void testDeleteExistente() {
        doNothing().when(facturaDetalleRepository).deleteById(1);
        facturaDetalleService.deleteById(1);
        verify(facturaDetalleRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteNoExistente() {
        doThrow(EmptyResultDataAccessException.class).when(facturaDetalleRepository).deleteById(999);
        assertThrows(EmptyResultDataAccessException.class, () -> facturaDetalleService.deleteById(999));
        verify(facturaDetalleRepository, times(1)).deleteById(999);
    }
}
