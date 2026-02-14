package com.distribuida.service;

import com.distribuida.dao.ClienteRepository;
import com.distribuida.model.Cliente;
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
public class ClienteServiceTestUnitaria {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setup() {
        cliente = new Cliente(1, "1701234567", "Juan", "Perez", "Calle 1", "0999999999", "juan@mail.com");
    }

    @Test
    void testFindAll() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        List<Cliente> result = clienteService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdExistente() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        Optional<Cliente> result = clienteService.findById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdCliente());
        verify(clienteRepository, times(1)).findById(1);
    }

    @Test
    void testFindByIdNoExistente() {
        when(clienteRepository.findById(999)).thenReturn(Optional.empty());
        Optional<Cliente> result = clienteService.findById(999);
        assertTrue(result.isEmpty());
        verify(clienteRepository, times(1)).findById(999);
    }

    @Test
    void testSave() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Cliente result = clienteService.save(cliente);
        assertNotNull(result);
        assertEquals(1, result.getIdCliente());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testUpdateExistente() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Cliente result = clienteService.update(cliente);
        assertNotNull(result);
        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testUpdateNoExistente() {
        when(clienteRepository.findById(999)).thenReturn(Optional.empty());
        Cliente result = clienteService.update(new Cliente(999, "x", "x", "x", "x", null, null));
        assertNull(result);
        verify(clienteRepository, times(1)).findById(999);
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void testDeleteExistente() {
        doNothing().when(clienteRepository).deleteById(1);
        clienteService.deleteById(1);
        verify(clienteRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteNoExistente() {
        doThrow(EmptyResultDataAccessException.class).when(clienteRepository).deleteById(999);
        assertThrows(EmptyResultDataAccessException.class, () -> clienteService.deleteById(999));
        verify(clienteRepository, times(1)).deleteById(999);
    }
}
