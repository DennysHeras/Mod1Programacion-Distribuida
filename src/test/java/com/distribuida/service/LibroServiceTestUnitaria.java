package com.distribuida.service;

import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
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
public class LibroServiceTestUnitaria {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    private Libro libro;

    @BeforeEach
    void setup() {
        libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitulo("Cien anos de soledad");
        libro.setEditorial("Editorial");
        libro.setNumPaginas(400);
        libro.setPrecio(15.5f);
    }

    @Test
    void testFindAll() {
        when(libroRepository.findAll()).thenReturn(List.of(libro));
        List<Libro> result = libroService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(libroRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdExistente() {
        when(libroRepository.findById(1)).thenReturn(Optional.of(libro));
        Optional<Libro> result = libroService.findById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdLibro());
        verify(libroRepository, times(1)).findById(1);
    }

    @Test
    void testFindByIdNoExistente() {
        when(libroRepository.findById(999)).thenReturn(Optional.empty());
        Optional<Libro> result = libroService.findById(999);
        assertTrue(result.isEmpty());
        verify(libroRepository, times(1)).findById(999);
    }

    @Test
    void testSave() {
        when(libroRepository.save(any(Libro.class))).thenReturn(libro);
        Libro result = libroService.save(libro);
        assertNotNull(result);
        assertEquals(1, result.getIdLibro());
        verify(libroRepository, times(1)).save(libro);
    }

    @Test
    void testUpdateExistente() {
        when(libroRepository.findById(1)).thenReturn(Optional.of(libro));
        when(libroRepository.save(any(Libro.class))).thenReturn(libro);
        Libro result = libroService.update(libro);
        assertNotNull(result);
        verify(libroRepository, times(1)).findById(1);
        verify(libroRepository, times(1)).save(any(Libro.class));
    }

    @Test
    void testUpdateNoExistente() {
        when(libroRepository.findById(999)).thenReturn(Optional.empty());
        Libro l = new Libro();
        l.setIdLibro(999);
        Libro result = libroService.update(l);
        assertNull(result);
        verify(libroRepository, times(1)).findById(999);
        verify(libroRepository, never()).save(any());
    }

    @Test
    void testDeleteExistente() {
        doNothing().when(libroRepository).deleteById(1);
        libroService.deleteById(1);
        verify(libroRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteNoExistente() {
        doThrow(EmptyResultDataAccessException.class).when(libroRepository).deleteById(999);
        assertThrows(EmptyResultDataAccessException.class, () -> libroService.deleteById(999));
        verify(libroRepository, times(1)).deleteById(999);
    }
}
