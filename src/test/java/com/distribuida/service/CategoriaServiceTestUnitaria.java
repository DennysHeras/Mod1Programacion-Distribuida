package com.distribuida.service;

import com.distribuida.dao.CategoriaRepository;
import com.distribuida.model.Categoria;
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
public class CategoriaServiceTestUnitaria {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    private Categoria categoria;

    @BeforeEach
    void setup() {
        categoria = new Categoria(1, "Novela", "Ficcion narrativa");
    }

    @Test
    void testFindAll() {
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));
        List<Categoria> result = categoriaService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdExistente() {
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        Optional<Categoria> result = categoriaService.findById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdCategoria());
        verify(categoriaRepository, times(1)).findById(1);
    }

    @Test
    void testFindByIdNoExistente() {
        when(categoriaRepository.findById(999)).thenReturn(Optional.empty());
        Optional<Categoria> result = categoriaService.findById(999);
        assertTrue(result.isEmpty());
        verify(categoriaRepository, times(1)).findById(999);
    }

    @Test
    void testSave() {
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);
        Categoria result = categoriaService.save(categoria);
        assertNotNull(result);
        assertEquals(1, result.getIdCategoria());
        verify(categoriaRepository, times(1)).save(categoria);
    }

    @Test
    void testUpdateExistente() {
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);
        Categoria result = categoriaService.update(categoria);
        assertNotNull(result);
        verify(categoriaRepository, times(1)).findById(1);
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void testUpdateNoExistente() {
        when(categoriaRepository.findById(999)).thenReturn(Optional.empty());
        Categoria result = categoriaService.update(new Categoria(999, "x", "x"));
        assertNull(result);
        verify(categoriaRepository, times(1)).findById(999);
        verify(categoriaRepository, never()).save(any());
    }

    @Test
    void testDeleteExistente() {
        doNothing().when(categoriaRepository).deleteById(1);
        categoriaService.deleteById(1);
        verify(categoriaRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteNoExistente() {
        doThrow(EmptyResultDataAccessException.class).when(categoriaRepository).deleteById(999);
        assertThrows(EmptyResultDataAccessException.class, () -> categoriaService.deleteById(999));
        verify(categoriaRepository, times(1)).deleteById(999);
    }
}
