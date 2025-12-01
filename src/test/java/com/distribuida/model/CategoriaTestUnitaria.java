package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaTestUnitaria {

    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        categoria = new Categoria(
                1,
                "Libros",
                "Categoría de libros generales"
        );
    }

    @Test
    public void testCategoriaConstructor() {
        assertAll("Validar datos de Categoria - constructor",
                () -> assertEquals(1, categoria.getIdCategoria()),
                () -> assertEquals("Libros", categoria.getCategoria()),
                () -> assertEquals("Categoría de libros generales", categoria.getDescripcion())
        );
    }

    @Test
    public void testCategoriaSetters() {
        categoria.setIdCategoria(2);
        categoria.setCategoria("Revistas");
        categoria.setDescripcion("Sección de revistas ilustradas");

        assertAll("Validar datos de Categoria - setters",
                () -> assertEquals(2, categoria.getIdCategoria()),
                () -> assertEquals("Revistas", categoria.getCategoria()),
                () -> assertEquals("Sección de revistas ilustradas", categoria.getDescripcion())
        );
    }

    @Test
    public void testCategoriaToString() {
        String str = categoria.toString();

        assertAll("Validar datos de Categoria - toString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Libros")),
                () -> assertTrue(str.contains("Categoría de libros generales"))
        );
    }
}