package com.distribuida.service;

import com.distribuida.dao.AutorRepository;
import com.distribuida.model.Autor;
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
public class AutorServiceTestUnitaria {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorServiceImpl autorService;

    private Autor autor;

    @BeforeEach
    void setup() {
        autor = new Autor(1, "Gabriel", "Garcia Marquez", "Colombia", "Escritor");
    }

    @Test
    void testFindAll() {
        when(autorRepository.findAll()).thenReturn(List.of(autor));
        List<Autor> result = autorService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(autorRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdExistente() {
        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));
        Optional<Autor> result = autorService.findById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdAutor());
        verify(autorRepository, times(1)).findById(1);
    }

    @Test
    void testFindByIdNoExistente() {
        when(autorRepository.findById(999)).thenReturn(Optional.empty());
        Optional<Autor> result = autorService.findById(999);
        assertTrue(result.isEmpty());
        verify(autorRepository, times(1)).findById(999);
    }

    @Test
    void testSave() {
        when(autorRepository.save(any(Autor.class))).thenReturn(autor);
        Autor result = autorService.save(autor);
        assertNotNull(result);
        assertEquals(1, result.getIdAutor());
        verify(autorRepository, times(1)).save(autor);
    }

    @Test
    void testUpdateExistente() {
        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));
        when(autorRepository.save(any(Autor.class))).thenReturn(autor);
        Autor result = autorService.update(autor);
        assertNotNull(result);
        verify(autorRepository, times(1)).findById(1);
        verify(autorRepository, times(1)).save(any(Autor.class));
    }

    @Test
    void testUpdateNoExistente() {
        when(autorRepository.findById(999)).thenReturn(Optional.empty());
        Autor result = autorService.update(new Autor(999, "x", "x", "x", "x"));
        assertNull(result);
        verify(autorRepository, times(1)).findById(999);
        verify(autorRepository, never()).save(any());
    }

    @Test
    void testDeleteExistente() {
        doNothing().when(autorRepository).deleteById(1);
        autorService.deleteById(1);
        verify(autorRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteNoExistente() {
        doThrow(EmptyResultDataAccessException.class).when(autorRepository).deleteById(999);
        assertThrows(EmptyResultDataAccessException.class, () -> autorService.deleteById(999));
        verify(autorRepository, times(1)).deleteById(999);
    }
}
